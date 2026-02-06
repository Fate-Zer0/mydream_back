package com.example.mydream_back.dao;

import com.example.mydream_back.model.UserActivity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserActivityDAO {
    /**
     * 插入用户动态
     */
    void insertActivity(UserActivity activity);
    
    /**
     * 获取最新动态列表
     * @param limit 返回数量
     * @return 动态列表（包含用户信息）
     */
    List<Map<String, Object>> getRecentActivities(@Param("limit") int limit);
    
    /**
     * 获取指定用户的动态列表
     */
    List<Map<String, Object>> getUserActivities(@Param("userId") String userId, @Param("limit") int limit);
}
