package com.example.mydream_back.services.model;

import com.example.mydream_back.dao.model.PianoRecordingDAO;
import com.example.mydream_back.model.PianoRecording;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PianoRecordingServiceImpl implements PianoRecordingService {

    @Autowired
    private PianoRecordingDAO pianoRecordingDAO;

    @Override
    public int uploadRecording(PianoRecording recording) {
        pianoRecordingDAO.insertRecording(recording);
        return recording.getRecordingId();
    }

    @Override
    public List<Map<String, Object>> getRecordingList(String userId, int limit) {
        return pianoRecordingDAO.getRecordingList(userId, limit);
    }

    @Override
    public PianoRecording playRecording(int recordingId) {
        pianoRecordingDAO.increasePlayCount(recordingId);
        return pianoRecordingDAO.getRecordingById(recordingId);
    }

    @Override
    public boolean deleteRecording(int recordingId, String userId) {
        pianoRecordingDAO.deleteRecording(recordingId, userId);
        return true;
    }
}
