package com.example.mydream_back.dto;

public class Tag {
    private String conn_id;
    private String tag_id;
    private String tag_name;
    private String tag_state;

    public String getConn_id() {
        return conn_id;
    }

    public void setConn_id(String conn_id) {
        this.conn_id = conn_id;
    }

    public String getTag_id() {
        return tag_id;
    }

    public void setTag_id(String tag_id) {
        this.tag_id = tag_id;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public String getTag_state() {
        return tag_state;
    }

    public void setTag_state(String tag_state) {
        this.tag_state = tag_state;
    }
}
