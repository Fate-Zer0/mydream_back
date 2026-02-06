package com.example.mydream_back.services.model;

import com.example.mydream_back.model.PianoRecording;

import java.util.List;
import java.util.Map;

public interface PianoRecordingService {
    /**
     * 上传录音
     */
    int uploadRecording(PianoRecording recording);
    
    /**
     * 获取榜单
     */
    List<Map<String, Object>> getRecordingList(String userId, int limit);
    
    /**
     * 播放录音（增加播放次数，返回数据）
     */
    PianoRecording playRecording(int recordingId);
    
    /**
     * 删除录音
     */
    boolean deleteRecording(int recordingId, String userId);
}
