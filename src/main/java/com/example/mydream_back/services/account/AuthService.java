package com.example.mydream_back.services.account;

import com.example.mydream_back.dto.UserDTO;
import com.example.mydream_back.model.User;

public interface AuthService {

    public UserDTO chickUserLogin(User user);

    public int getUserCountByUsername(String user_name);
}
