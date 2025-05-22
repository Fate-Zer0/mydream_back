package com.example.mydream_back.services.account;

import com.example.mydream_back.dao.UserDAO;
import com.example.mydream_back.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserDAO userDAO;
    @Override
    public Boolean chickUserLogin(User user) {
        List<User> users = userDAO.getUsers(user);
        if(users.size() > 0){
            return true;
        }
        return false;
    }
}
