package com.example.mydream_back.dto;

import java.util.List;

public class MsgBoard {

    private UserDTO user;
    private int msg_id;
    private String msg_content;
    private String msg_content1;
    private String msg_time;
    private int msg_upcount;
    private int msg_downcount;
    private int msg_istop;
    private int msg_parid;
    private int msg_type;
    private int msg_ordid;
    private int isup;
    private int isdown;
    private int hfcount;
    private List<MsgBoard> hf_msgbds;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public int getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(int msg_id) {
        this.msg_id = msg_id;
    }

    public String getMsg_content() {
        return msg_content;
    }

    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }

    public String getMsg_content1() {
        return msg_content1;
    }

    public void setMsg_content1(String msg_content1) {
        this.msg_content1 = msg_content1;
    }

    public String getMsg_time() {
        return msg_time;
    }

    public void setMsg_time(String msg_time) {
        this.msg_time = msg_time;
    }

    public int getMsg_upcount() {
        return msg_upcount;
    }

    public void setMsg_upcount(int msg_upcount) {
        this.msg_upcount = msg_upcount;
    }

    public int getMsg_downcount() {
        return msg_downcount;
    }

    public void setMsg_downcount(int msg_downcount) {
        this.msg_downcount = msg_downcount;
    }

    public int getMsg_istop() {
        return msg_istop;
    }

    public void setMsg_istop(int msg_istop) {
        this.msg_istop = msg_istop;
    }

    public int getMsg_parid() {
        return msg_parid;
    }

    public void setMsg_parid(int msg_parid) {
        this.msg_parid = msg_parid;
    }

    public int getMsg_type() {
        return msg_type;
    }

    public void setMsg_type(int msg_type) {
        this.msg_type = msg_type;
    }

    public int getMsg_ordid() {
        return msg_ordid;
    }

    public void setMsg_ordid(int msg_ordid) {
        this.msg_ordid = msg_ordid;
    }

    public int getIsup() {
        return isup;
    }

    public void setIsup(int isup) {
        this.isup = isup;
    }

    public int getIsdown() {
        return isdown;
    }

    public void setIsdown(int isdown) {
        this.isdown = isdown;
    }

    public int getHfcount() {
        return hfcount;
    }

    public void setHfcount(int hfcount) {
        this.hfcount = hfcount;
    }

    public List<MsgBoard> getHf_msgbds() {
        return hf_msgbds;
    }

    public void setHf_msgbds(List<MsgBoard> hf_msgbds) {
        this.hf_msgbds = hf_msgbds;
    }
}
