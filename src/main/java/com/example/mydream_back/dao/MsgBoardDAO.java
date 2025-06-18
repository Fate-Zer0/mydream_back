package com.example.mydream_back.dao;

import com.example.mydream_back.dto.MsgBoard;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MsgBoardDAO {

    public void insertMsgBoard(MsgBoard msgb);
    public List<MsgBoard> selectMsgBoards(MsgBoard msgb);
    public void addUpCount(MsgBoard msgb);
    public void subUpCount(MsgBoard msgb);
    public void addDownCount(MsgBoard msgb);
    public void subDownCount(MsgBoard msgb);

}
