package com.example.mydream_back.dto;

public class FriendInfo {
    private String fri_id;
    private UserDTO user;
    private UserDTO friend;
    private String createtime;
    private String state;//-2 删除 -1 拒绝 0 等待 1 同意

    public String getFri_id() {
        return fri_id;
    }

    public void setFri_id(String fri_id) {
        this.fri_id = fri_id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public UserDTO getFriend() {
        return friend;
    }

    public void setFriend(UserDTO friend) {
        this.friend = friend;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
