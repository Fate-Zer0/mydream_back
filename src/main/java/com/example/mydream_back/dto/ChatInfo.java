package com.example.mydream_back.dto;

import com.example.mydream_back.model.User;

public class ChatInfo {
    private UserInfo chat_user;
    private Message lastMessage;
    private int unread_count;
    private int chat_count;

    public UserInfo getChat_user() {
        return chat_user;
    }

    public void setChat_user(UserInfo chat_user) {
        this.chat_user = chat_user;
    }

    public Message getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }

    public int getUnread_count() {
        return unread_count;
    }

    public void setUnread_count(int unread_count) {
        this.unread_count = unread_count;
    }

    public int getChat_count() {
        return chat_count;
    }

    public void setChat_count(int chat_count) {
        this.chat_count = chat_count;
    }
}
