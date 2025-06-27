package com.example.mydream_back.services.account;

import com.example.mydream_back.dto.UserDTO;
import com.example.mydream_back.dto.UserInfo;
import com.example.mydream_back.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public void signInToday(String user_id);
    public int getConsecutiveSignInDays(String user_id);
    public boolean checkIfSignedToday(String user_id);
    public Map<String,Object> getSignInInfo(String user_id);
    public List<String> getSignInDatesByYearAndMonth(String user_id, int year, int month);
    public UserInfo getUserInfoByUserId(String user_id);
    public void updateUserInfo(UserInfo userInfo);
    public void addUserInfo(UserInfo userInfo);
}
