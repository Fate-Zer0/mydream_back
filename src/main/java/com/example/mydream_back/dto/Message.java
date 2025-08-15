package com.example.mydream_back.dto;

public class Message {
    private String msg_id;
    private String msg_content;
    private String createtime;
    private String send_id;//发送者id
    private String receive_id;//接收者id
    private String msg_type;//0:文字 1:图片 2:文件
    private String msg_state;///0:未读 1:已读
    private FileInfoDTO file_info;
    private String send_type;//0:群聊 1:私聊

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getMsg_content() {
        return msg_content;
    }

    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getSend_id() {
        return send_id;
    }

    public void setSend_id(String send_id) {
        this.send_id = send_id;
    }

    public String getReceive_id() {
        return receive_id;
    }

    public void setReceive_id(String receive_id) {
        this.receive_id = receive_id;
    }

    public String getMsg_type() {
        return msg_type;
    }

    public void setMsg_type(String msg_type) {
        this.msg_type = msg_type;
    }

    public String getMsg_state() {
        return msg_state;
    }

    public void setMsg_state(String msg_state) {
        this.msg_state = msg_state;
    }

    public FileInfoDTO getFile_info() {
        return file_info;
    }

    public void setFile_info(FileInfoDTO file_info) {
        this.file_info = file_info;
    }

    public String getSend_type() {
        return send_type;
    }

    public void setSend_type(String send_type) {
        this.send_type = send_type;
    }
}
