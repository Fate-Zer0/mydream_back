package com.example.mydream_back.dao;

import com.example.mydream_back.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDAO {
    public List<User> getUsers(User user);
    public int getUserCountByUsername(String user_name);
    public List<User> addUser(User user);
    public void signInToday(@Param("userId") String user_id);
    public int getConsecutiveSignInDays(@Param("userId") String userId);
    public int getSignInCount(@Param("userId") String userId);
    Map<String, Long> isSignedToday(@Param("userId") String userId);

}
