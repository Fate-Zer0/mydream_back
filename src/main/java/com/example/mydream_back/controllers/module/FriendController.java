package com.example.mydream_back.controllers.module;

import com.example.mydream_back.dao.UserDAO;
import com.example.mydream_back.dto.FriendInfo;
import com.example.mydream_back.dto.ReturnValue;
import com.example.mydream_back.dto.UserDTO;
import com.example.mydream_back.dto.UserInfo;
import com.example.mydream_back.services.model.friend.FriendService;
import com.example.mydream_back.utils.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/api/module/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @GetMapping("/chickIsFriend")
    public ResponseEntity<ReturnValue<Boolean>> chickIsFriend(@RequestParam String user_id,String friend_id){
        ReturnValue<Boolean> returnValue = new ReturnValue<Boolean>();
        Boolean isFriend = friendService.chickIsFriend(user_id,friend_id);
        returnValue.setRetValue(isFriend);
        returnValue.isSuccess();
        return ResponseEntity.ok(returnValue);
    }

    @PostMapping("/addFriend")
    public ResponseEntity<ReturnValue<Boolean>> addFriend(@RequestParam String user_id,String friend_id){
        ReturnValue<Boolean> returnValue = new ReturnValue<Boolean>();
        String state = friendService.getFriendState(user_id,friend_id);
        if(StringHelper.isEmpty(state)){
            String state1 = friendService.getFriendState(friend_id,user_id);
            String add_state = "0";
            if(StringHelper.isNotEmpty(state1) && "0".equals(state1)){
                add_state = "1";
                FriendInfo friendInfo = friendService.getFriendInfo(friend_id,user_id,null);
                friendService.updateFriendStateByFri_id(friendInfo.getFri_id(),add_state);
            }else if(StringHelper.isNotEmpty(state1) && "1".equals(state1)){
                add_state = "1";
            }
            if(friendService.addFriend(user_id,friend_id,add_state)){
                returnValue.isSuccess();
            }else{
                returnValue.isError();
                returnValue.setRetDesc("添加失败!");
            }
        }else if("1".equals(state)){
            returnValue.isFail();
            returnValue.setRetDesc("已是好友,请勿重复添加!");
        }else{
            returnValue.isFail();
            returnValue.setRetDesc("已发送添加请求,请勿重复发送!");
        }
        return ResponseEntity.ok(returnValue);
    }

    @PostMapping("/updateFriendState")
    public ResponseEntity<ReturnValue<Boolean>> updateFriendState(@RequestParam String fri_id,String state){
        ReturnValue<Boolean> returnValue = new ReturnValue<Boolean>();
        FriendInfo friendInfo = friendService.getFriendInfoByFri_id(fri_id);
        if(!friendInfo.getState().equals("0")){
            returnValue.isFail();
            returnValue.setRetDesc("当前申请已被处理或取消!");
            return ResponseEntity.ok(returnValue);
        }

        friendService.updateFriendStateByFri_id(fri_id,state);
        if(StringHelper.isNotEmpty(state) && "1".equals(state)){
            friendService.addFriend(friendInfo.getFriend().getUser_id(),friendInfo.getUser().getUser_id(),state);
        }
        returnValue.setRetValue(true);
        returnValue.isSuccess();
        return ResponseEntity.ok(returnValue);
    }

    @GetMapping("/getAddFriendInfo")
    public ResponseEntity<ReturnValue<List<FriendInfo>>> getAddFriendInfo(@RequestParam String user_id, String friend_id,String state){
        ReturnValue<List<FriendInfo>> returnValue = new ReturnValue<List<FriendInfo>>();
        List<FriendInfo> friendInfolist = friendService.getFriendInfoList(user_id,friend_id,state);
        returnValue.isSuccess();
        returnValue.setRetValue(friendInfolist);
        return ResponseEntity.ok(returnValue);
    }

    @GetMapping("/searchUser")
    public ResponseEntity<ReturnValue<List<UserDTO>>> searchUser(@RequestParam String user_name){
        ReturnValue<List<UserDTO>> returnValue = new ReturnValue<List<UserDTO>>();
        UserDTO userDTO = new UserDTO();
        userDTO.setUser_name(user_name);
        List<UserDTO> userInfolist = friendService.searchUser(userDTO);
        returnValue.setRetValue(userInfolist);
        returnValue.isSuccess();
        return ResponseEntity.ok(returnValue);
    }

}
