package com.example.mydream_back.dto;

public class SecurityQuestion {
    private UserDTO user;
    private String secq_id;
    private String question;
    private String answer;
    private String createtime;
    private String state;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getSecq_id() {
        return secq_id;
    }

    public void setSecq_id(String secq_id) {
        this.secq_id = secq_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
