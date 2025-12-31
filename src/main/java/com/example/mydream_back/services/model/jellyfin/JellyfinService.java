package com.example.mydream_back.services.model.jellyfin;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class JellyfinService {

    private static final Logger log = LoggerFactory.getLogger(JellyfinService.class);

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${jellyfin.base-url}")
    private String baseUrl;

    @Value("${jellyfin.api-key}")
    private String apiKey;

    public JellyfinService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    /**
     * 触发 Jellyfin 扫描指定文件
     */
    public void triggerScan(String filePath) {
        String url = baseUrl + "/Library/Media/Updated";
        String jsonBody = """
        {
          "Updates": [
            {
              "Path": "%s",
              "UpdateType": "Created"
            }
          ]
        }
        """.formatted(filePath);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "MediaBrowser Token=" + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            log.info("Jellyfin scan triggered for {}, status: {}", filePath, response.getStatusCode());
        } catch (Exception e) {
            log.error("Failed to trigger Jellyfin scan for file: " + filePath, e);
        }
    }

    /**
     * 根据文件路径查找 Jellyfin 中对应的 Item ID
     */
    public String getItemIdByPath(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            log.warn("getItemIdByPath called with invalid path: {}", filePath);
            return null;
        }

//        String encodedPath = URLEncoder.encode(filePath, StandardCharsets.UTF_8)
//                .replace("+", "%20"); // URLEncoder 会把空格转成 +，但 Jellyfin 需要 %20

        String url = String.format(
                "%s/Items?Recursive=true&MediaTypes=Video,Audio&LocationTypes=FileSystem&Path=%s",
                baseUrl,
                filePath
        );


        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "MediaBrowser Token=" + apiKey);

        HttpEntity<Void> request = new HttpEntity<>(headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

            // 检查响应体是否为空
            if (response.getBody() == null || response.getBody().isEmpty()) {
                log.warn("Jellyfin returned empty response for path query");
                return null;
            }

            JsonNode root = objectMapper.readTree(response.getBody());
            JsonNode items = root.get("Items");

            log.info("Constructed URL: {}", url);
            log.info("Headers: {}", headers);

            if (items == null || !items.isArray()) {
                log.warn("Jellyfin response missing 'Items' array");
                return null;
            }

            for (JsonNode item : items) {
                // 安全获取 Path 和 Id
                JsonNode pathNode = item.get("Path");
                JsonNode idNode = item.get("Id");

                if (pathNode != null && idNode != null) {
                    String jellyfinPath = pathNode.asText();
                    if (filePath.equals(jellyfinPath)) {
                        return idNode.asText();
                    }
                }
                // 如果某个 item 缺少 Path 或 Id，跳过（不 crash）
            }

            log.debug("No matching Jellyfin item found for path: {}", filePath);
            return null;

        } catch (Exception e) {
            log.error("Error querying Jellyfin item by path: " + filePath, e);
            return null;
        }
    }

    /**
     * 更新 Jellyfin 中某个媒体项的显示名称（标题）
     */
    public void updateItemName(String itemId, String newName) {
        if (itemId == null || newName == null || newName.trim().isEmpty()) {
            log.warn("Skipping update: invalid itemId or name");
            return;
        }

        String url = baseUrl + "/Items/" + itemId;
        String jsonBody = """
        {
          "Name": "%s"
        }
        """.formatted(newName.replace("\"", "\\\"")); // 防止 JSON 注入

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "MediaBrowser Token=" + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);

        try {
            restTemplate.exchange(url, HttpMethod.POST, request, String.class);
            log.info("Successfully updated Jellyfin item {} title to: {}", itemId, newName);
        } catch (Exception e) {
            log.error("Failed to update Jellyfin item name for ID: " + itemId, e);
        }
    }

    public void updateItemMetadata(String itemId, String name, String overview) {
        if (itemId == null) {
            log.warn("Cannot update metadata: itemId is null");
            return;
        }

        // 构建 JSON（自动处理 null）
        StringBuilder json = new StringBuilder("{\n");
        if (name != null) {
            json.append("  \"Name\": \"").append(escapeJson(name)).append("\",\n");
        }
        if (overview != null) {
            json.append("  \"Overview\": \"").append(escapeJson(overview)).append("\",\n");
        }
        // 移除最后一个逗号（如果存在）
        if (json.toString().endsWith(",\n")) {
            json.setLength(json.length() - 2);
        }
        json.append("\n}");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "MediaBrowser Token=" + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(json.toString(), headers);

        try {
            restTemplate.exchange(baseUrl + "/Items/" + itemId, HttpMethod.POST, request, String.class);
            log.info("Updated Jellyfin item {} with name='{}', hasOverview={}",
                    itemId, name, overview != null);
        } catch (Exception e) {
            log.error("Failed to update metadata for item: " + itemId, e);
        }
    }

    // 简单 JSON 字符串转义（防注入）
    private String escapeJson(String input) {
        if (input == null) return "";
        return input.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    /**
     * 异步处理 Jellyfin 媒体项：触发扫描 → 轮询获取 ID → 更新标题
     *
     * @param absoluteFilePath 文件绝对路径，如 /home/zfate/.../a1b2c3.mp4
     * @param displayName      用户希望显示的标题
     */
    @Async
    public void asyncHandleMediaFile(String absoluteFilePath, String displayName, String overview) {
        if (absoluteFilePath == null) {
            log.warn("Skip async Jellyfin handling due to null file path");
            return;
        }

        triggerScan(absoluteFilePath);

        // 轮询最多 15 秒找 Item ID
        String itemId = null;
        int maxRetries = 18;
        for (int i = 0; i < maxRetries; i++) {
            try {
                Thread.sleep(10000);
                itemId = getItemIdByPath(absoluteFilePath);
                if (itemId != null) break;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("Interrupted while waiting for Jellyfin item", e);
                return;
            }
        }

        if (itemId != null) {
            updateItemMetadata(itemId, displayName, overview); // ← 新方法
        } else {
            log.warn("Failed to find Jellyfin item after {} seconds: {}", maxRetries, absoluteFilePath);
        }
    }
}