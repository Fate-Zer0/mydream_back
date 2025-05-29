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
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserDAO userDAO;
    @Override
    public UserDTO chickUserLogin(User user) {
        List<User> users = userDAO.getUsers(user);
        if(users.size() > 0){
            User t_user = users.get(0);
            UserDTO userDTO = new UserDTO();
            userDTO.setUser_id(t_user.getUser_id());
            userDTO.setUser_name(t_user.getUser_name());
            return userDTO;
        }
        return null;
    }
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
