package com.example.mydream_back.services.activity;

import com.example.mydream_back.model.UserActivity;

import java.util.List;
import java.util.Map;

public interface UserActivityService {
    /**
     * 创建用户动态
     */
    void createActivity(UserActivity activity);
    
    /**
     * 创建签到动态
     */
    void createSignInActivity(String userId, int consecutiveDays, int points);
    
    /**
     * 获取最新动态列表
     */
    List<Map<String, Object>> getRecentActivities(int limit);
    
    /**
     * 获取指定用户的动态列表
     */
    List<Map<String, Object>> getUserActivities(String userId, int limit);
}
