package com.example.mydream_back.utils;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CustomHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

        // 从 headers 中获取 user_id
        String user_id = request.getHeaders().getFirst("user_id");

        if (user_id != null && !user_id.isEmpty()) {
            attributes.put("user_id", user_id);
        } else {
            // 可选：从 URL 参数中回退获取
            Map<String, String> params = parseQueryParams(request.getURI());
            user_id = params.get("user_id");
            if (user_id != null && !user_id.isEmpty()) {
                attributes.put("user_id", user_id);
            } else {
                // 拒绝连接或设置默认值
                // return false; // 阻止握手
                attributes.put("user_id", "anonymous");
            }
        }

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {
        // 握手完成后可选操作
    }

    private Map<String, String> parseQueryParams(URI uri) {
        Map<String, String> queryParams = new LinkedHashMap<>();
        String query = uri.getQuery();

        if (query != null) {
            for (String pair : query.split("&")) {
                int idx = pair.indexOf("=");
                String key = idx > 0 ? pair.substring(0, idx) : pair;
                String value = idx > 0 && pair.length() > idx + 1 ? pair.substring(idx + 1) : null;

                key = java.net.URLDecoder.decode(key, StandardCharsets.UTF_8);
                if (value != null) {
                    value = java.net.URLDecoder.decode(value, StandardCharsets.UTF_8);
                }

                queryParams.put(key, value);
            }
        }

        return queryParams;
    }
}