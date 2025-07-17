package com.example.mydream_back.dao.model;


import com.example.mydream_back.dto.FriendInfo;
import com.example.mydream_back.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface FriendDAO {

    int chickIsFriend(@Param("user_id") String user_id,@Param("friend_id") String friend_id);
    String getFriendState(@Param("user_id") String user_id,@Param("friend_id") String friend_id);
    FriendInfo getFriendInfoByFri_id(@Param("fri_id") String fri_id);
    FriendInfo getFriendInfo(@Param("user_id") String user_id, @Param("friend_id") String friend_id, @Param("state") String state);
    List<FriendInfo> getFriendInfoList(@Param("user_id") String user_id, @Param("friend_id") String friend_id, @Param("state") String state);
    int addFriend(FriendInfo friendInfo);
    int updateFriendState(FriendInfo friendInfo);
    int updateFriendStateByFri_id(FriendInfo friendInfo);
    List<UserDTO> searchUser(UserDTO userDTO);
}
