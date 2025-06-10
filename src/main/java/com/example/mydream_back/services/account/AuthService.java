package com.example.mydream_back.services.account;

import com.example.mydream_back.dto.UserDTO;
import com.example.mydream_back.model.User;

public interface AuthService {

    UserDTO chickUserLogin(User user);
    int getUserCountByUsername(String user_name);
    int signUp(User user);
}
