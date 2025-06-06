package com.example.mydream_back.services.account;

import com.example.mydream_back.dao.UserDAO;
import com.example.mydream_back.dto.UserDTO;
import com.example.mydream_back.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            userDTO.setUser_img(t_user.getUser_img());
            return userDTO;
        }
        return null;
    }
    public int getUserCountByUsername(String user_name){
        return userDAO.getUserCountByUsername(user_name);
    }
}
