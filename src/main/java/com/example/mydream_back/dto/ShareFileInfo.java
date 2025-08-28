package com.example.mydream_back.dto;

import java.util.List;

public class ShareFileInfo {

    private String share_id;
    private FileInfoDTO share_file;
    private UserDTO share_user;
    private String file_name;
    private String share_time;
    private String share_state;
    private String description;
    private List<Tag> tags;
    private String file_type;
    private String file_size;

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }
    public String getShare_id() {
        return share_id;
    }

    public void setShare_id(String share_id) {
        this.share_id = share_id;
    }

    public FileInfoDTO getShare_file() {
        return share_file;
    }

    public void setShare_file(FileInfoDTO share_file) {
        this.share_file = share_file;
    }

    public UserDTO getShare_user() {
        return share_user;
    }

    public void setShare_user(UserDTO share_user) {
        this.share_user = share_user;
    }

    public String getShare_time() {
        return share_time;
    }

    public void setShare_time(String share_time) {
        this.share_time = share_time;
    }

    public String getShare_state() {
        return share_state;
    }

    public void setShare_state(String share_state) {
        this.share_state = share_state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public String getFile_size() {
        return file_size;
    }

    public void setFile_size(String file_size) {
        this.file_size = file_size;
    }
}
