package com.example.mydream_back.dao.model;

import com.example.mydream_back.dto.ChatInfo;
import com.example.mydream_back.dto.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatDAO {
    List<Message> getChatMessageList(Message message);
    List<Message> getChatMessageListBySendidAndReceiceid(Message message);
    Message getLastChatMessage(Message message);
    void addChatMessage(Message message);
    List<ChatInfo> getChatInfoList(@Param("user_id")String user_id);
    void updateUnreadMessage(Message message);
}
