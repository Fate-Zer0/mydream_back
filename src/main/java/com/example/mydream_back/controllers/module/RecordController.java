package com.example.mydream_back.controllers.module;


import com.example.mydream_back.dto.ReturnValue;
import com.example.mydream_back.model.Record;
import com.example.mydream_back.services.model.record.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/api/module/record")
public class RecordController {

    @Autowired
    private RecordService recService;

    @GetMapping("/addRecord")
    public ResponseEntity<ReturnValue<String>> addRecord(@RequestParam String user_id,@RequestParam String passive_id, @RequestParam String rec_type, @RequestParam int obj_id){
        Record rec = new Record();
        rec.setRec_type(rec_type);
        rec.setObj_id(obj_id);
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        rec.setRecordtime(sdf.format(System.currentTimeMillis()));
        rec.setActiveman(user_id);
        rec.setPassiveman(passive_id);
        if(recService.chickIsAdded(rec)){
           recService.unEffect(rec);
        }else{
            recService.addRecord(rec);
        }
        ReturnValue returnValue = new ReturnValue();
        returnValue.isSuccess();
        return  ResponseEntity.ok(returnValue);
    }

}
