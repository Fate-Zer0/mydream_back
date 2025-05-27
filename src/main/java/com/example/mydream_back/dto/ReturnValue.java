package com.example.mydream_back.dto;

public class ReturnValue<T> {
    private String retCode;
    private String retDesc;
    private T retValue;

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetDesc() {
        return retDesc;
    }

    public void setRetDesc(String retDesc) {
        this.retDesc = retDesc;
    }

    public T getRetValue() {
        return retValue;
    }

    public void setRetValue(T retValue) {
        this.retValue = retValue;
    }

    public void isSuccess(){
        this.retCode = ReturnCode.SUCCESS;
    }
    public void isError(){
        this.retCode = ReturnCode.ERROR;
    }
    public void isFail(){
        this.retCode = ReturnCode.FAIL;
    }
}
