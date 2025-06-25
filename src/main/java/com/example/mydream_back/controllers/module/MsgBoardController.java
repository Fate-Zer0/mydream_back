package com.example.mydream_back.controllers.module;

import com.example.mydream_back.dto.MsgBoardDTO;
import com.example.mydream_back.model.MsgBoard;
import com.example.mydream_back.dto.ReturnValue;
import com.example.mydream_back.dto.UserDTO;
import com.example.mydream_back.services.model.msgBoard.MsgBoardService;
import com.example.mydream_back.utils.TimeCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/module/msgBoard")
public class MsgBoardController {

    @Autowired
    private MsgBoardService msgBoardService;

    @GetMapping("/getMsgBoardInfo")
    public ResponseEntity<ReturnValue<List<MsgBoard>>> getMsgBoardInfo(@RequestParam String user_id){
        ReturnValue<List<MsgBoard>> returnValue = new ReturnValue<List<MsgBoard>>();
        List<MsgBoard> msgBoards = new ArrayList<>();
        MsgBoard mB= new MsgBoard();
        UserDTO user = new UserDTO();
        user.setUser_id(user_id);
        mB.setUser(user);
        mB.setMsg_type(61000);
        msgBoards = msgBoardService.GetMsgBoards(mB);
        returnValue.setRetValue(msgBoards);
        returnValue.isSuccess();
        return ResponseEntity.ok(returnValue);
    }

    @PostMapping("/addMsgBoard")
    public ResponseEntity<ReturnValue<String>> addMsgBoard(@RequestBody MsgBoardDTO msgBoardDTO){
        ReturnValue<String> returnValue = new ReturnValue<String>();

        MsgBoard msgBoard = msgBoardDTO.toMsgBoard();
        int msg_id = msgBoard.getMsg_id();
        int msg_parid = 0;
        if(msg_id != 0){
            msg_parid = msg_id;
        }
        if(msg_parid > 0){
            msgBoard.setMsg_parid(msg_parid);
            msgBoard.setMsg_istop(0);
        }else{
            msgBoard.setMsg_istop(1);
        }
        msgBoard.setMsg_time(TimeCreator.nowStr());
        msgBoard.setMsg_type(61000);
        msgBoardService.addMsgBoard(msgBoard);

        returnValue.isSuccess();
        return ResponseEntity.ok(returnValue);
    }

}
