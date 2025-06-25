package com.example.mydream_back.dao.model;

import com.example.mydream_back.model.Record;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface RecordDAO {
    public int chickIsAdded(Record rec);
    public void insertRecord(Record rec);
    public void unEffect(Record rec);
}
