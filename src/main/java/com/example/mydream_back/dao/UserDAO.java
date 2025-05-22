package com.example.mydream_back.dao;

import com.example.mydream_back.dto.UserDTO;
import com.example.mydream_back.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDAO {
    public List<User> getUsers(User user);
    public List<User> addUser(User user);

}
