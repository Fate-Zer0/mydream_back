package com.example.mydream_back.services.model.friend;

import com.example.mydream_back.dto.FriendInfo;
import com.example.mydream_back.dto.UserDTO;

import java.util.List;

public interface FriendService {

    Boolean chickIsFriend(String user_id,String friend_id);
    String getFriendState(String user_id, String friend_id);
    Boolean addFriend(String user_id,String friend_id,String state);
    FriendInfo getFriendInfoByFri_id(String fri_id);
    FriendInfo getFriendInfo(String user_id, String friend_id, String state);
    List<FriendInfo> getFriendInfoList(String user_id, String friend_id, String state);
    void updateFriendStateByFri_id(String fri_id,String state);
    List<UserDTO> searchUser(UserDTO userDTO);
}
