package com.example.mydream_back.services.account;

import com.example.mydream_back.dao.UserDAO;
import com.example.mydream_back.dto.SecurityQuestion;
import com.example.mydream_back.dto.UserDTO;
import com.example.mydream_back.dto.UserInfo;
import com.example.mydream_back.model.User;
import com.example.mydream_back.services.activity.UserActivityService;
import com.example.mydream_back.utils.StringHelper;
import com.example.mydream_back.utils.TimeCreator;
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
    
    @Autowired
    private UserActivityService userActivityService;
    public void signInToday(String userId) {
        userDAO.signInToday(userId);
        
        // 获取当前连续签到天数
        int consecutiveDays = userDAO.getContinuousSignInDays(userId);
        
        // 根据连续签到天数计算积分奖励
        int pointsReward = calculateSignInPoints(consecutiveDays);
        
        UserInfo userInfo = userDAO.getUserInfoByUserId(userId);
        if(StringHelper.isEmpty(userInfo.getUser_points())){
            userInfo = new UserInfo();
            UserDTO user = new UserDTO();
            user.setUser_id(userId);
            userInfo.setUser(user);
            userInfo.setUser_points(String.valueOf(pointsReward));
            userDAO.addUserInfo(userInfo);
        }else{
            String user_points = userInfo.getUser_points();
            userInfo.setUser_points(String.valueOf(Integer.parseInt(user_points) + pointsReward));
            userDAO.updateUserInfo(userInfo);
        }
        
        // 创建签到动态
        userActivityService.createSignInActivity(userId, consecutiveDays, pointsReward);
    }
    
    /**
     * 根据连续签到天数计算积分奖励
     * @param consecutiveDays 连续签到天数
     * @return 积分奖励
     */
    private int calculateSignInPoints(int consecutiveDays) {
        // 基础奖励规则
        if (consecutiveDays == 1) return 5;      // 第1天
        if (consecutiveDays == 2) return 6;      // 第2天
        if (consecutiveDays == 3) return 7;      // 第3天
        if (consecutiveDays <= 6) return 10;     // 第4-6天
        if (consecutiveDays == 7) return 20;     // 第7天（周奖励）
        if (consecutiveDays <= 13) return 15;    // 第8-13天
        if (consecutiveDays == 14) return 50;    // 第14天（双周奖励）
        if (consecutiveDays <= 20) return 15;    // 第15-20天
        if (consecutiveDays == 21) return 100;   // 第21天（三周里程碑）
        if (consecutiveDays <= 29) return 20;    // 第22-29天
        if (consecutiveDays == 30) return 200;   // 第30天（月度大奖）
        if (consecutiveDays <= 59) return 25;    // 第31-59天
        if (consecutiveDays == 60) return 300;   // 第60天（双月大奖）
        if (consecutiveDays <= 99) return 30;    // 第61-99天
        if (consecutiveDays == 100) return 500;  // 第100天（传奇里程碑）
        
        // 100天以后，每天奖励35积分
        return 35;
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
        
        // 添加今日和明日奖励信息
        int todayPoints = 0;
        int tomorrowPoints = 0;
        
        if (isSigned) {
            // 已签到，显示今天获得的积分
            todayPoints = calculateSignInPoints(consecutiveSignInDays);
            tomorrowPoints = calculateSignInPoints(consecutiveSignInDays + 1);
        } else {
            // 未签到，显示今天可以获得的积分
            todayPoints = calculateSignInPoints(consecutiveSignInDays + 1);
            tomorrowPoints = calculateSignInPoints(consecutiveSignInDays + 2);
        }
        
        map.put("todayPoints", todayPoints);
        map.put("tomorrowPoints", tomorrowPoints);
        map.put("currentDay", isSigned ? consecutiveSignInDays : consecutiveSignInDays + 1);

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

    public void addUserStatus(UserInfo userInfo){
        userDAO.addUserStatus(userInfo);
    }

    public void updateUserStatus(UserInfo userInfo){
        userDAO.updateUserStatus(userInfo);
    }

    @Override
    public void changerUserStatus(String user_id,String status){
        UserInfo userInfo = new UserInfo();
        UserDTO user = new UserDTO();
        user.setUser_id(user_id);
        userInfo.setUser_status_code(status);
        userInfo.setLast_sign_in_date(TimeCreator.nowStr());
        userInfo.setUser(user);
        UserInfo userInfo_t = getUserInfoByUserId(user_id);
        if(StringHelper.isEmpty(userInfo_t.getLast_sign_in_date())){
            userDAO.addUserStatus(userInfo);
        }else{
            userDAO.updateUserStatus(userInfo);
        }
    }
    
    @Override
    public List<Map<String, Object>> getSignInRanking(int limit) {
        if (limit <= 0) {
            limit = 10; // 默认返回前10名
        }
        return userDAO.getSignInRanking(limit);
    }
}
