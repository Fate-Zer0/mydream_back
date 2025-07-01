package com.example.mydream_back.model;

public class FileInfo {
    private String originalName;      // 原始文件名
    private String storedName;        // 存储时的文件名（UUID + 扩展名）
    private String filePath;          // 文件存储路径
    private String fileUrl;           // 可访问的 URL（可选）
    private long size;                // 文件大小
    private String contentType;       // MIME 类型
    private String extension;         // 文件扩展名

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getStoredName() {
        return storedName;
    }

    public void setStoredName(String storedName) {
        this.storedName = storedName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
