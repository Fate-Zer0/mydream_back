package com.example.mydream_back.model;

public class Record {
    private int rec_id;
    private String rec_type;
    private int obj_id;
    private String recordtime;
    private String activeman;
    private String passiveman;

    public int getRec_id() {
        return rec_id;
    }

    public void setRec_id(int rec_id) {
        this.rec_id = rec_id;
    }

    public String getRec_type() {
        return rec_type;
    }

    public void setRec_type(String rec_type) {
        this.rec_type = rec_type;
    }

    public int getObj_id() {
        return obj_id;
    }

    public void setObj_id(int obj_id) {
        this.obj_id = obj_id;
    }

    public String getRecordtime() {
        return recordtime;
    }

    public void setRecordtime(String recordtime) {
        this.recordtime = recordtime;
    }

    public String getActiveman() {
        return activeman;
    }

    public void setActiveman(String activeman) {
        this.activeman = activeman;
    }

    public String getPassiveman() {
        return passiveman;
    }

    public void setPassiveman(String passiveman) {
        this.passiveman = passiveman;
    }
}
