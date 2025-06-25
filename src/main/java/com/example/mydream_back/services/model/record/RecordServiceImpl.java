package com.example.mydream_back.services.model.record;

import com.example.mydream_back.dao.model.RecordDAO;
import com.example.mydream_back.model.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService{

    @Autowired
    private RecordDAO recDap;
    @Override
    public Boolean chickIsAdded(Record rec){
        int count = recDap.chickIsAdded(rec);
        if(count > 0){
            return true;
        }
        return false;
    }

    @Override
    public void addRecord(Record rec){
        recDap.insertRecord(rec);
    }
    @Override
    public void unEffect(Record rec){
        recDap.unEffect(rec);
    }
}
