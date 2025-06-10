package com.example.mydream_back.dao;

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
    int getSignInCount(@Param("userId") String userId);
    Map<String, Long> isSignedToday(@Param("userId") String userId);
}
