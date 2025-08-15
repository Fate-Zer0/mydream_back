package com.example.mydream_back.services.model.chat;

import com.example.mydream_back.dto.ChatInfo;
import com.example.mydream_back.dto.Message;

import java.util.List;

public interface ChatService {
    void addChatMessage(Message message);
    List<Message> getChatMessageList(String send_id, String receive_id, String msg_state);
    List<Message> getChatMessageListBySendidAndReceiceid(String userid, String friendid);
    Message getLastMessage(String userid, String friendid);
    List<ChatInfo> getChatInfoList(String user_id);
    void updateUnreadMessage(Message message);
}
