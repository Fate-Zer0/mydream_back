package com.example.mydream_back.services.activity;

import com.example.mydream_back.dao.UserActivityDAO;
import com.example.mydream_back.model.UserActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserActivityServiceImpl implements UserActivityService {
    
    @Autowired
    private UserActivityDAO userActivityDAO;
    
    @Override
    public void createActivity(UserActivity activity) {
        if (activity.getIsPublic() == null) {
            activity.setIsPublic(1);
        }
        userActivityDAO.insertActivity(activity);
    }
    
    @Override
    public void createSignInActivity(String userId, int consecutiveDays, int points) {
        UserActivity activity = new UserActivity();
        activity.setUserId(userId);
        activity.setActivityType("SIGN_IN");
        activity.setActivityTitle("每日签到");
        
        // 根据连续天数生成不同的描述
        String desc;
        if (consecutiveDays == 1) {
            desc = String.format("签到成功，获得 %d 积分", points);
        } else if (consecutiveDays == 7) {
            desc = String.format("🔥 连续签到 %d 天！获得 %d 积分奖励", consecutiveDays, points);
        } else if (consecutiveDays == 30) {
            desc = String.format("🎉 连续签到 %d 天！获得 %d 积分大礼包", consecutiveDays, points);
        } else if (consecutiveDays == 100) {
            desc = String.format("👑 连续签到 %d 天！获得 %d 积分超级奖励", consecutiveDays, points);
        } else if (consecutiveDays % 10 == 0) {
            desc = String.format("✨ 连续签到 %d 天，获得 %d 积分", consecutiveDays, points);
        } else {
            desc = String.format("连续签到 %d 天，获得 %d 积分", consecutiveDays, points);
        }
        
        activity.setActivityDesc(desc);
        activity.setIsPublic(1);
        
        userActivityDAO.insertActivity(activity);
    }
    
    @Override
    public List<Map<String, Object>> getRecentActivities(int limit) {
        if (limit <= 0) {
            limit = 20;
        }
        return userActivityDAO.getRecentActivities(limit);
    }
    
    @Override
    public List<Map<String, Object>> getUserActivities(String userId, int limit) {
        if (limit <= 0) {
            limit = 20;
        }
        return userActivityDAO.getUserActivities(userId, limit);
    }
}
