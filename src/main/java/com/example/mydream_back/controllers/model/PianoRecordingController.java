package com.example.mydream_back.controllers.model;

import com.example.mydream_back.dto.ReturnValue;
import com.example.mydream_back.model.PianoRecording;
import com.example.mydream_back.services.model.PianoRecordingService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/piano")
public class PianoRecordingController {

    @Autowired
    private PianoRecordingService pianoRecordingService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final int MAX_DURATION = 120; // 最长120秒

    /**
     * 上传钢琴录音
     */
    @PostMapping("/uploadRecording")
    public ReturnValue uploadRecording(@RequestBody Map<String, Object> request) {
        try {
            String userId = (String) request.get("userId");
            String recordingName = (String) request.get("recordingName");
            Object recordingDataObj = request.get("recordingData");
            
            // 处理 isPublic 的类型转换（前端可能发送number类型）
            Object isPublicObj = request.getOrDefault("isPublic", 1);
            Integer isPublic = isPublicObj instanceof Integer ? 
                (Integer) isPublicObj : 
                Integer.valueOf(isPublicObj.toString());

            System.out.println("接收到上传请求，用户ID: " + userId + ", 录音名称: " + recordingName);
            
            String recordingData = objectMapper.writeValueAsString(recordingDataObj);
            System.out.println("录音数据JSON: " + (recordingData.length() > 200 ? recordingData.substring(0, 200) + "..." : recordingData));

            // 解析录音数据，计算时长和音符数
            JsonNode dataNode = objectMapper.readTree(recordingData);
            double duration = 0;
            int noteCount = 0;

            if (dataNode.isArray()) {
                System.out.println("录音事件数量: " + dataNode.size());
                int index = 0;
                for (JsonNode note : dataNode) {
                    // 前端使用 timestamp 字段（单位：毫秒）
                    JsonNode timestampNode = note.get("timestamp");
                    if (timestampNode != null) {
                        double timestamp = timestampNode.asDouble();
                        if (timestamp > duration) {
                            duration = timestamp;
                        }
                        noteCount++;
                    } else {
                        System.out.println("警告：第 " + index + " 个事件没有timestamp字段: " + note.toString());
                    }
                    index++;
                }
                // 将毫秒转换为秒
                duration = duration / 1000.0;
                System.out.println("计算时长: " + duration + "秒, 音符数: " + noteCount);
            }

            // 检查时长限制
            if (duration > MAX_DURATION) {
                ReturnValue<String> returnValue = new ReturnValue<>();
                returnValue.isError();
                returnValue.setRetDesc("录音时长超过限制（最长120秒）");
                return returnValue;
            }

            PianoRecording recording = new PianoRecording();
            recording.setUserId(userId);
            recording.setRecordingName(recordingName);
            recording.setRecordingData(recordingData);
            recording.setDuration(BigDecimal.valueOf(duration));
            recording.setNoteCount(noteCount);
            recording.setIsPublic(isPublic);

            int recordingId = pianoRecordingService.uploadRecording(recording);
            
            ReturnValue<Integer> returnValue = new ReturnValue<>();
            returnValue.isSuccess();
            returnValue.setRetValue(recordingId);
            return returnValue;

        } catch (Exception e) {
            e.printStackTrace();
            ReturnValue<String> returnValue = new ReturnValue<>();
            returnValue.isError();
            returnValue.setRetDesc("上传失败：" + e.getMessage());
            return returnValue;
        }
    }

    /**
     * 获取榜单
     */
    @GetMapping("/getRecordingList")
    public ReturnValue<List<Map<String, Object>>> getRecordingList(
            @RequestParam String userId,
            @RequestParam(defaultValue = "20") int limit) {
        try {
            List<Map<String, Object>> list = pianoRecordingService.getRecordingList(userId, limit);
            ReturnValue<List<Map<String, Object>>> returnValue = new ReturnValue<>();
            returnValue.isSuccess();
            returnValue.setRetValue(list);
            return returnValue;
        } catch (Exception e) {
            e.printStackTrace();
            ReturnValue<List<Map<String, Object>>> returnValue = new ReturnValue<>();
            returnValue.isError();
            returnValue.setRetDesc("获取榜单失败");
            return returnValue;
        }
    }

    /**
     * 播放录音（获取数据并增加播放次数）
     */
    @GetMapping("/playRecording")
    public ReturnValue<PianoRecording> playRecording(@RequestParam int recordingId) {
        try {
            PianoRecording recording = pianoRecordingService.playRecording(recordingId);
            if (recording == null) {
                ReturnValue<PianoRecording> returnValue = new ReturnValue<>();
                returnValue.isError();
                returnValue.setRetDesc("录音不存在");
                return returnValue;
            }
            ReturnValue<PianoRecording> returnValue = new ReturnValue<>();
            returnValue.isSuccess();
            returnValue.setRetValue(recording);
            return returnValue;
        } catch (Exception e) {
            e.printStackTrace();
            ReturnValue<PianoRecording> returnValue = new ReturnValue<>();
            returnValue.isError();
            returnValue.setRetDesc("播放失败");
            return returnValue;
        }
    }

    /**
     * 删除录音
     */
    @PostMapping("/deleteRecording")
    public ReturnValue<String> deleteRecording(@RequestBody Map<String, Object> request) {
        try {
            int recordingId = (int) request.get("recordingId");
            String userId = (String) request.get("userId");
            
            boolean success = pianoRecordingService.deleteRecording(recordingId, userId);
            ReturnValue<String> returnValue = new ReturnValue<>();
            if (success) {
                returnValue.isSuccess();
                returnValue.setRetDesc("删除成功");
            } else {
                returnValue.isError();
                returnValue.setRetDesc("删除失败");
            }
            return returnValue;
        } catch (Exception e) {
            e.printStackTrace();
            ReturnValue<String> returnValue = new ReturnValue<>();
            returnValue.isError();
            returnValue.setRetDesc("删除失败");
            return returnValue;
        }
    }
}
