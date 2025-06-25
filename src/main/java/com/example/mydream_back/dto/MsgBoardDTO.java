package com.example.mydream_back.dto;

import com.example.mydream_back.model.MsgBoard;

import java.util.List;

public class MsgBoardDTO {
    private UserDTO user;
    private int msg_id;
    private String msg_content;
    private int msg_upcount;
    private Boolean isup;
    private String msg_time;
    private List<MsgBoardDTO> hf_msgbds;

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

    public int getMsg_upcount() {
        return msg_upcount;
    }

    public void setMsg_upcount(int msg_upcount) {
        this.msg_upcount = msg_upcount;
    }

    public Boolean getIsup() {
        return isup;
    }

    public void setIsup(Boolean isup) {
        this.isup = isup;
    }

    public String getMsg_time() {
        return msg_time;
    }

    public void setMsg_time(String msg_time) {
        this.msg_time = msg_time;
    }

    public List<MsgBoardDTO> getHf_msgbds() {
        return hf_msgbds;
    }

    public void setHf_msgbds(List<MsgBoardDTO> hf_msgbds) {
        this.hf_msgbds = hf_msgbds;
    }

    public MsgBoard toMsgBoard() {
        MsgBoard msgBoard = new MsgBoard();
        msgBoard.setMsg_id(this.msg_id);
        msgBoard.setMsg_content(this.msg_content);
        msgBoard.setMsg_upcount(this.msg_upcount);
        msgBoard.setMsg_time(this.msg_time);
        msgBoard.setIsup(this.isup ? 1 : 0);
        msgBoard.setUser(this.user);
        return msgBoard;
    }
}
