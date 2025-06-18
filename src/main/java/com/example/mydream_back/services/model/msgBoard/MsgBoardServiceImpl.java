package com.example.mydream_back.services.model.msgBoard;

import com.example.mydream_back.dao.MsgBoardDAO;
import com.example.mydream_back.dto.MsgBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MsgBoardServiceImpl implements MsgBoardService {

    @Autowired
    private MsgBoardDAO msgBoardDao;

    @Override
    public void addMsgBoard(MsgBoard msgb) {
        msgBoardDao.insertMsgBoard(msgb);
    }
    @Override
    public List<MsgBoard> GetMsgBoards(MsgBoard msgb) {
        return msgBoardDao.selectMsgBoards(msgb);
    }
    @Override
    public void addUpCount(MsgBoard msgb){
        msgBoardDao.addUpCount(msgb);
    }
    @Override
    public void subUpCount(MsgBoard msgb){
        msgBoardDao.subUpCount(msgb);
    }
    @Override
    public void addDownCount(MsgBoard msgb){
        msgBoardDao.addDownCount(msgb);
    }
    @Override
    public void subDownCount(MsgBoard msgb){
        msgBoardDao.subDownCount(msgb);
    }

}
