package com.example.mydream_back.services.account;

import com.example.mydream_back.dto.SecurityQuestion;
import com.example.mydream_back.dto.UserDTO;
import com.example.mydream_back.dto.UserInfo;
import com.example.mydream_back.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserService {
    void signInToday(String user_id);
    int getConsecutiveSignInDays(String user_id);
    boolean checkIfSignedToday(String user_id);
    Map<String,Object> getSignInInfo(String user_id);
    List<String> getSignInDatesByYearAndMonth(String user_id, int year, int month);
    UserInfo getUserInfoByUserId(String user_id);
    void updateUserInfo(UserInfo userInfo);
    void addUserInfo(UserInfo userInfo);
    void InsertUserFile(UserDTO user);
    void updateUserFile(UserDTO user);
    void addSecQuestion(SecurityQuestion secQuestion);
    void updateSecQuestion(SecurityQuestion secQuestion);
    List<SecurityQuestion> getUserSecQuestion(String userId);
    List<SecurityQuestion> getUserSecQuestionByUsername(String user_name);
    Boolean chickAnswer(String secq_id,String answer);
    void updateUserPassword(String user_name,String password);
    void addUserStatus(UserInfo userInfo);
    void updateUserStatus(UserInfo userInfo);
}
