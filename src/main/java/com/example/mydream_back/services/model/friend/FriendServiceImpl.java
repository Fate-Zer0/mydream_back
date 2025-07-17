package com.example.mydream_back.services.model.friend;

import com.example.mydream_back.dao.model.FriendDAO;
import com.example.mydream_back.dto.FriendInfo;
import com.example.mydream_back.dto.UserDTO;
import com.example.mydream_back.model.User;
import com.example.mydream_back.utils.TimeCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService{
    @Autowired
    private FriendDAO friendDAO;

    @Override
    public Boolean chickIsFriend(String user_id, String friend_id) {
        int count = friendDAO.chickIsFriend(user_id,friend_id);
        if(count > 0){
            return true;
        }
        return false;
    }
    @Override
    public String getFriendState(String user_id, String friend_id) {
        return friendDAO.getFriendState(user_id,friend_id);
    }

    @Override
    public Boolean addFriend(String user_id,String friend_id,String state) {
        FriendInfo friendInfo = new FriendInfo();
        UserDTO user = new UserDTO();
        UserDTO friend = new UserDTO();
        user.setUser_id(user_id);
        friend.setUser_id(friend_id);
        friendInfo.setUser(user);
        friendInfo.setFriend(friend);
        friendInfo.setCreatetime(TimeCreator.nowStr());
        friendInfo.setState(state);
        int count = friendDAO.addFriend(friendInfo);
        if(count > 0){
            return true;
        }
        return false;
    }

    @Override
    public FriendInfo getFriendInfoByFri_id(String fri_id) {
        return friendDAO.getFriendInfoByFri_id(fri_id);
    }
    @Override
    public FriendInfo getFriendInfo(String user_id, String friend_id, String state) {
        return friendDAO.getFriendInfo(user_id,friend_id,state);
    }

    @Override
    public List<FriendInfo> getFriendInfoList(String user_id, String friend_id, String state) {
        return friendDAO.getFriendInfoList(user_id,friend_id,state);
    }
    @Override
    public void updateFriendStateByFri_id(String fri_id,String state) {
        FriendInfo friendInfo = getFriendInfoByFri_id(fri_id);
        friendInfo.setState(state);
        friendDAO.updateFriendStateByFri_id(friendInfo);
    }
    @Override
    public List<UserDTO> searchUser(UserDTO userDTO){
        return friendDAO.searchUser(userDTO);
    }
}
