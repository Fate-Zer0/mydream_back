package com.example.mydream_back.dao;

import com.example.mydream_back.dto.SecurityQuestion;
import com.example.mydream_back.dto.UserDTO;
import com.example.mydream_back.dto.UserInfo;
import com.example.mydream_back.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDAO {
    List<User> getUsers(User user);
    int getUserCountByUsername(String user_name);
    int addUser(User user);
    void signInToday(@Param("userId") String user_id);
    int getConsecutiveSignInDays(@Param("userId") String userId);
    int getContinuousSignInDays(@Param("userId") String userId);
    List<String> getSignInDatesByYearAndMonth(@Param("userId") String userId,@Param("year") int year, @Param("maxMonth") int maxMonth, @Param("minMonth") int minMonth);
    int getSignInCount(@Param("userId") String userId);
    Map<String, Long> isSignedToday(@Param("userId") String userId);
    UserInfo getUserInfoByUserId(@Param("userId") String userId);
    int updateUser(UserDTO user);
    int updateUserPassword(User user);
    int updateUserInfo(UserInfo userInfo);
    void addUserInfo(UserInfo userInfo);
    void InsertUserFile(UserDTO user);
    void updateUserFile(UserDTO user);
    void addSecQuestion(SecurityQuestion secQuestion);
    void updateSecQuestion(SecurityQuestion secQuestion);
    List<SecurityQuestion> getUserSecQuestion(@Param("userId") String userId);
    List<SecurityQuestion> getUserSecQuestionByUsername(@Param("user_name") String user_name);
    int chickAnswer(@Param("secq_id") String secq_id,@Param("answer") String answer);
}
