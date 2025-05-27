package com.example.mydream_back.dao;

import com.example.mydream_back.dto.UserDTO;
import com.example.mydream_back.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDAO {
    public List<User> getUsers(User user);
    public List<User> addUser(User user);
    public void signInToday(@Param("userId") String user_id);
    public int getConsecutiveSignInDays(@Param("userId") String userId);
    Map<String, Integer> isSignedToday(@Param("userId") String userId);

}
