package com.example.mydream_back.services.account;

import com.example.mydream_back.dao.UserDAO;
import com.example.mydream_back.dto.SecurityQuestion;
import com.example.mydream_back.dto.UserDTO;
import com.example.mydream_back.dto.UserInfo;
import com.example.mydream_back.model.User;
import com.example.mydream_back.utils.StringHelper;
import org.apache.ibatis.annotations.Param;
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
        UserInfo userInfo = userDAO.getUserInfoByUserId(userId);
        if(StringHelper.isEmpty(userInfo.getUser_points())){
            userInfo = new UserInfo();
            UserDTO user = new UserDTO();
            user.setUser_id(userId);
            userInfo.setUser(user);
            userInfo.setUser_points("5");
            userDAO.addUserInfo(userInfo);
        }else{
            String user_points = userInfo.getUser_points();
            userInfo.setUser_points(String.valueOf(Integer.parseInt(user_points) + 5));
            userDAO.updateUserInfo(userInfo);
        }
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
        int maxConsecutiveSignInDays = userDAO.getConsecutiveSignInDays(user_id);
        int consecutiveSignInDays = userDAO.getContinuousSignInDays(user_id);
        int signInCount = userDAO.getSignInCount(user_id);
        Map<String, Long> result = userDAO.isSignedToday(user_id);
        boolean isSigned = result.get("is_signed_today") == 1;

        map.put("maxConsecutiveSignInDays",maxConsecutiveSignInDays);
        map.put("consecutiveSignInDays",consecutiveSignInDays);
        map.put("signInCount",signInCount);
        map.put("isSigned",isSigned);

        return map;
    }

    public List<String> getSignInDatesByYearAndMonth(String user_id,int year, int month){
        return userDAO.getSignInDatesByYearAndMonth(user_id,year,month+1,month-1);
    }

    public UserInfo getUserInfoByUserId(String user_id){
        return userDAO.getUserInfoByUserId(user_id);
    }

    public void updateUserInfo(UserInfo userInfo){
        User user = new User();
        String user_name = userInfo.getUser().getUser_name();
        user.setUser_name(user_name);
        if(StringHelper.isNotEmpty(user_name) && userDAO.getUsers(user).size() == 0){
            userDAO.updateUser(userInfo.getUser());
        }

        UserInfo userInfo_t = userDAO.getUserInfoByUserId(userInfo.getUser().getUser_id());
        if(StringHelper.isEmpty(userInfo_t.getUser_points())){
            userInfo.setUser_points("0");
            userDAO.addUserInfo(userInfo);
        }else{
            userDAO.updateUserInfo(userInfo);
        }
    }

    public void addUserInfo(UserInfo userInfo){
        userDAO.addUserInfo(userInfo);
    }
    public void InsertUserFile(UserDTO user){
        userDAO.InsertUserFile(user);
    }
    public void updateUserFile(UserDTO user){
        userDAO.updateUserFile(user);
    }
    public void addSecQuestion(SecurityQuestion secQuestion){
        userDAO.addSecQuestion(secQuestion);
    }
    public void updateSecQuestion(SecurityQuestion secQuestion){
        userDAO.updateSecQuestion(secQuestion);
    }
    public List<SecurityQuestion> getUserSecQuestion(String userId){
        return userDAO.getUserSecQuestion(userId);
    }
    public List<SecurityQuestion> getUserSecQuestionByUsername(String user_name){
        return userDAO.getUserSecQuestionByUsername(user_name);
    }

    public Boolean chickAnswer(String secq_id,String answer){
        int count = userDAO.chickAnswer(secq_id,answer);
        if(count > 0){
            return true;
        }
        return false;
    }

    public void updateUserPassword(String user_name,String password){
        User user = new User();
        user.setUser_name(user_name);
        user.setUser_pw(password);
        userDAO.updateUserPassword(user);
    }
}
