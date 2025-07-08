package com.example.mydream_back.dto;

public class UserInfo {
    private UserDTO user;
    private String user_create_date;
    private String last_sign_in_date;
    private String user_points;
    private String user_sex_code;
    private String user_sex_name;
    private String user_grjj;
    private String user_status_code;
    private String user_status_name;

    public String getUser_status_code() {
        return user_status_code;
    }

    public void setUser_status_code(String user_status_code) {
        this.user_status_code = user_status_code;
    }

    public String getUser_status_name() {
        return user_status_name;
    }

    public void setUser_status_name(String user_status_name) {
        this.user_status_name = user_status_name;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getUser_create_date() {
        return user_create_date;
    }

    public void setUser_create_date(String user_create_date) {
        this.user_create_date = user_create_date;
    }

    public String getLast_sign_in_date() {
        return last_sign_in_date;
    }

    public void setLast_sign_in_date(String last_sign_in_date) {
        this.last_sign_in_date = last_sign_in_date;
    }

    public String getUser_points() {
        return user_points;
    }

    public void setUser_points(String user_points) {
        this.user_points = user_points;
    }

    public String getUser_sex_code() {
        return user_sex_code;
    }

    public void setUser_sex_code(String user_sex_code) {
        this.user_sex_code = user_sex_code;
    }

    public String getUser_sex_name() {
        return user_sex_name;
    }

    public void setUser_sex_name(String user_sex_name) {
        this.user_sex_name = user_sex_name;
    }

    public String getUser_grjj() {
        return user_grjj;
    }

    public void setUser_grjj(String user_grjj) {
        this.user_grjj = user_grjj;
    }
}
