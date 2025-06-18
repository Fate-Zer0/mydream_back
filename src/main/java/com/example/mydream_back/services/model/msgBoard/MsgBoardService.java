package com.example.mydream_back.services.model.msgBoard;

import com.example.mydream_back.dto.MsgBoard;

import java.util.List;

public interface MsgBoardService {

    public void addMsgBoard(MsgBoard msgb);
    public List<MsgBoard> GetMsgBoards(MsgBoard msgb);
    public void addUpCount(MsgBoard msgb);
    public void subUpCount(MsgBoard msgb);
    public void addDownCount(MsgBoard msgb);
    public void subDownCount(MsgBoard msgb);

}
