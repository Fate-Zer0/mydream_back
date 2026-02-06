package com.example.mydream_back.controllers.activity;

import com.example.mydream_back.model.UserActivity;
import com.example.mydream_back.services.activity.UserActivityService;
import com.example.mydream_back.dto.ReturnValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/activity")
public class UserActivityController {
    
    @Autowired
    private UserActivityService userActivityService;
    
    /**
     * 获取最新动态列表
     */
    @GetMapping("/getRecentActivities")
    public ResponseEntity<ReturnValue<List<Map<String, Object>>>> getRecentActivities(
            @RequestParam(defaultValue = "20") int limit) {
        ReturnValue<List<Map<String, Object>>> returnValue = new ReturnValue<>();
        List<Map<String, Object>> activities = userActivityService.getRecentActivities(limit);
        returnValue.setRetValue(activities);
        returnValue.isSuccess();
        return ResponseEntity.ok(returnValue);
    }
    
    /**
     * 获取指定用户的动态列表
     */
    @GetMapping("/getUserActivities")
    public ResponseEntity<ReturnValue<List<Map<String, Object>>>> getUserActivities(
            @RequestParam String userId,
            @RequestParam(defaultValue = "20") int limit) {
        ReturnValue<List<Map<String, Object>>> returnValue = new ReturnValue<>();
        List<Map<String, Object>> activities = userActivityService.getUserActivities(userId, limit);
        returnValue.setRetValue(activities);
        returnValue.isSuccess();
        return ResponseEntity.ok(returnValue);
    }
    
    /**
     * 创建用户动态（通用接口）
     */
    @PostMapping("/createActivity")
    public ResponseEntity<ReturnValue<Boolean>> createActivity(@RequestBody UserActivity activity) {
        ReturnValue<Boolean> returnValue = new ReturnValue<>();
        try {
            userActivityService.createActivity(activity);
            returnValue.setRetValue(true);
            returnValue.isSuccess();
        } catch (Exception e) {
            returnValue.setRetValue(false);
            returnValue.isError();
            returnValue.setRetDesc("创建动态失败：" + e.getMessage());
        }
        return ResponseEntity.ok(returnValue);
    }
}
