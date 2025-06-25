package com.example.mydream_back.services.model.record;

import com.example.mydream_back.model.Record;

public interface RecordService {
    public Boolean chickIsAdded(Record rec);
    public void addRecord(Record rec);
    public void unEffect(Record rec);

}
