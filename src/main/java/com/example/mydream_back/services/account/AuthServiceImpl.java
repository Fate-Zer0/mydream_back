package com.example.mydream_back.services.account;

import com.example.mydream_back.dao.UserDAO;
import com.example.mydream_back.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public void signInToday(String userId) {
        userDAO.signInToday(userId);
    }
    public int getConsecutiveSignInDays(String user_id){
        return userDAO.getConsecutiveSignInDays(user_id);
    }
    public boolean checkIfSignedToday(String user_id) {
        Map<String, Integer> result = userDAO.isSignedToday(user_id);
        return result.get("is_signed_today") == 1;
    }
}
