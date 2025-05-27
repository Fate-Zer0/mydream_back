package com.example.mydream_back.services.account;

import com.example.mydream_back.model.User;

public interface AuthService {

    public Boolean chickUserLogin(User user);
    public void signInToday(String user_id);
    public int getConsecutiveSignInDays(String user_id);
    public boolean checkIfSignedToday(String user_id);
}
