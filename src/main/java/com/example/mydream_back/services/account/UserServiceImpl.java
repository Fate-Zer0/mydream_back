package com.example.mydream_back.services.account;

import com.example.mydream_back.dao.UserDAO;
import com.example.mydream_back.dto.UserDTO;
import com.example.mydream_back.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    public void signInToday(String userId) {
        userDAO.signInToday(userId);
    }
    public int getConsecutiveSignInDays(String user_id){
        return userDAO.getConsecutiveSignInDays(user_id);
    }
    public boolean checkIfSignedToday(String user_id) {
        Map<String, Long> result = userDAO.isSignedToday(user_id);
        return result.get("is_signed_today") == 1;
    }

    public Map<String,Object> getSignInInfo(String user_id){
        Map<String,Object> map = new HashMap<>();
        int consecutiveSignInDays = userDAO.getConsecutiveSignInDays(user_id);
        int signInCount = userDAO.getSignInCount(user_id);
        Map<String, Long> result = userDAO.isSignedToday(user_id);
        boolean isSigned = result.get("is_signed_today") == 1;

        map.put("consecutiveSignInDays",consecutiveSignInDays);
        map.put("signInCount",signInCount);
        map.put("isSigned",isSigned);

        return map;
    }
}
