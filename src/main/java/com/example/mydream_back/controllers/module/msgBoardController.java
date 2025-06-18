package com.example.mydream_back.controllers.module;

import com.example.mydream_back.dto.MsgBoard;
import com.example.mydream_back.dto.ReturnValue;
import com.example.mydream_back.dto.UserDTO;
import com.example.mydream_back.model.User;
import com.example.mydream_back.services.model.msgBoard.MsgBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/module/msgBoard")
public class msgBoardController {

    @Autowired
    private MsgBoardService msgBoardService;

    @GetMapping("/getMsgBoardInfo")
    public ResponseEntity<ReturnValue<List<MsgBoard>>> chickUsernameIsHave(@RequestParam String user_id){
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

}
