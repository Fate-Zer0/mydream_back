package com.example.mydream_back.dto;

public class UserDTO {
    private String user_id;
    private String user_name;
    private FileInfo user_img;

    public FileInfo getUser_img() {
        return user_img;
    }

    public void setUser_img(FileInfo user_img) {
        this.user_img = user_img;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
