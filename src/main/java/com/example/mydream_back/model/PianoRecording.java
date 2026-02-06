package com.example.mydream_back.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PianoRecording {
    private Integer recordingId;
    private String userId;
    private String recordingName;
    private String recordingData;
    private BigDecimal duration;
    private Integer noteCount;
    private LocalDateTime createTime;
    private Integer playCount;
    private Integer likeCount;
    private Integer isPublic;

    public Integer getRecordingId() {
        return recordingId;
    }

    public void setRecordingId(Integer recordingId) {
        this.recordingId = recordingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRecordingName() {
        return recordingName;
    }

    public void setRecordingName(String recordingName) {
        this.recordingName = recordingName;
    }

    public String getRecordingData() {
        return recordingData;
    }

    public void setRecordingData(String recordingData) {
        this.recordingData = recordingData;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }

    public Integer getNoteCount() {
        return noteCount;
    }

    public void setNoteCount(Integer noteCount) {
        this.noteCount = noteCount;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Integer playCount) {
        this.playCount = playCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }
}
