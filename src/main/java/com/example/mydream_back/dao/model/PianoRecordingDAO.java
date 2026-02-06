package com.example.mydream_back.dao.model;

import com.example.mydream_back.model.PianoRecording;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PianoRecordingDAO {
    /**
     * 保存录音
     */
    void insertRecording(PianoRecording recording);
    
    /**
     * 获取录音榜单（按点赞数排序）
     */
    List<Map<String, Object>> getRecordingList(@Param("userId") String userId, @Param("limit") int limit);
    
    /**
     * 增加播放次数
     */
    void increasePlayCount(@Param("recordingId") int recordingId);
    
    /**
     * 获取录音详情
     */
    PianoRecording getRecordingById(@Param("recordingId") int recordingId);
    
    /**
     * 删除录音
     */
    void deleteRecording(@Param("recordingId") int recordingId, @Param("userId") String userId);
}
