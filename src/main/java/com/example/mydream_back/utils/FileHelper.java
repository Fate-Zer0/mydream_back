package com.example.mydream_back.utils;

import com.example.mydream_back.model.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class FileHelper {
    // 支持的图片类型（可根据需要扩展）
    private static final List<String> ALLOWED_CONTENT_TYPES = Arrays.asList(
            "image/jpeg", "image/png", "image/gif", "image/webp"
    );

    // 最大文件大小（例如：10MB）
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024;

    // 文件保存根目录（可通过配置注入）
    private static String baseStoragePath = "/usr/myd/img/";

    public void setBaseStoragePath(String baseStoragePath) {
        this.baseStoragePath = baseStoragePath;
    }

    /**
     * 上传文件并返回文件信息
     *
     * @param file        要上传的文件
     * @param subDir      子目录（如 user/123）
     * @return            文件信息对象
     * @throws IOException 如果上传失败
     */
    public static FileInfo uploadFile(MultipartFile file, String subDir) throws IOException {
        // 校验文件是否为空
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("上传的文件不能为空");
        }

        // 校验文件大小
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("文件大小超过限制");
        }

        // 校验文件类型
        String contentType = file.getContentType();
        if (contentType == null || !ALLOWED_CONTENT_TYPES.contains(contentType)) {
            throw new IllegalArgumentException("不允许的文件类型");
        }

        // 获取文件扩展名
        String originalFilename = file.getOriginalFilename();
        String fileExtension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        // 构建安全的文件名
        String safeFileName = UUID.randomUUID() + fileExtension;

        // 构建完整路径
        Path targetPath = Paths.get(baseStoragePath, subDir);
        if (!Files.exists(targetPath)) {
            Files.createDirectories(targetPath);
        }

        Path finalPath = targetPath.resolve(safeFileName);
        file.transferTo(finalPath);

        // 构造返回结果
        FileInfo fileInfo = new FileInfo();
        fileInfo.setOriginalName(originalFilename);
        fileInfo.setStoredName(safeFileName);
        fileInfo.setFilePath(finalPath.toString());
        fileInfo.setFileUrl(subDir + "/" + safeFileName);
        fileInfo.setSize(file.getSize());
        fileInfo.setContentType(contentType);
        fileInfo.setExtension(fileExtension);

        return fileInfo;
    }
}
