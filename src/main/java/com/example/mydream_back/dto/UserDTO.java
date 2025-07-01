package com.example.mydream_back.dto;

import com.example.mydream_back.model.User;

public class UserDTO {
    private String user_id;
    private String user_name;
    private FileInfoDTO user_img;

    public User toUser(){
        User user = new User();
        user.setUser_id(user_id);
        user.setUser_name(user_name);
        user.setUser_img(user_img);
        return user;
    }

    public FileInfoDTO getUser_img() {
        return user_img;
    }

    public void setUser_img(FileInfoDTO user_img) {
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
