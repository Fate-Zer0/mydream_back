package com.example.mydream_back.model;

import com.example.mydream_back.dto.FileInfoDTO;

public class User {

    private String user_id;
    private String user_name;
    private String user_pw;
    private FileInfoDTO user_img;
    private String createtime;

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

    public String getUser_pw() {
        return user_pw;
    }

    public void setUser_pw(String user_pw) {
        this.user_pw = user_pw;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
