package com.example.mydream_back.dto;

import java.util.List;

public class PageBox {
    private String pb_id;
    private String pb_name;
    private String description;
    private String category;
    private FileInfoDTO pb_img;
    private String pb_url;
    private List<Tag> tags;
    private String status;
    private String pb_state;
    private String isFavorite;
    private String createtime;

    public String getPb_id() {
        return pb_id;
    }

    public void setPb_id(String pb_id) {
        this.pb_id = pb_id;
    }

    public String getPb_name() {
        return pb_name;
    }

    public void setPb_name(String pb_name) {
        this.pb_name = pb_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public FileInfoDTO getPb_img() {
        return pb_img;
    }

    public void setPb_img(FileInfoDTO pb_img) {
        this.pb_img = pb_img;
    }

    public String getPb_url() {
        return pb_url;
    }

    public void setPb_url(String pb_url) {
        this.pb_url = pb_url;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPb_state() {
        return pb_state;
    }

    public void setPb_state(String pb_state) {
        this.pb_state = pb_state;
    }

    public String getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(String isFavorite) {
        this.isFavorite = isFavorite;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
