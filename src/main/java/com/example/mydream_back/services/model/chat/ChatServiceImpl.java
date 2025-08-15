package com.example.mydream_back.services.model.chat;

import com.example.mydream_back.dao.UserDAO;
import com.example.mydream_back.dao.model.ChatDAO;
import com.example.mydream_back.dto.ChatInfo;
import com.example.mydream_back.dto.Message;
import com.example.mydream_back.dto.UserInfo;
import com.example.mydream_back.utils.TimeCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService{

    @Autowired
    private ChatDAO chatDAO;
    @Autowired
    private UserDAO userDAO;

    @Override
    public List<Message> getChatMessageList(String send_id, String receive_id, String msg_state) {
        Message message = new Message();
        message.setSend_id(send_id);
        message.setReceive_id(receive_id);
        message.setMsg_state(msg_state);
        return chatDAO.getChatMessageList(message);
    }

    @Override
    public List<Message> getChatMessageListBySendidAndReceiceid(String userid, String friendid) {
        Message message = new Message();
        message.setSend_id(userid);
        message.setReceive_id(friendid);
        message.setSend_type("1");
        chatDAO.updateUnreadMessage(message);
        return chatDAO.getChatMessageListBySendidAndReceiceid(message);
    }

    @Override
    public Message getLastMessage(String userid, String friendid){
        Message message = new Message();
        message.setSend_id(friendid);
        message.setReceive_id(userid);
        message.setSend_type("1");
        chatDAO.updateUnreadMessage(message);
        return chatDAO.getLastChatMessage(message);
    }
    @Override
    public void addChatMessage(Message message) {
        message.setCreatetime(TimeCreator.nowStr());
        chatDAO.addChatMessage(message);
    }
    @Override
    public List<ChatInfo> getChatInfoList(String user_id){
        List<ChatInfo> chatInfoList = chatDAO.getChatInfoList(user_id);
        for (ChatInfo chatInfo : chatInfoList) {
            UserInfo userInfo = chatInfo.getChat_user();
            userInfo = userDAO.getUserInfoByUserId(userInfo.getUser().getUser_id());
            chatInfo.setChat_user(userInfo);
        }
        return chatInfoList;
    }
    @Override
    public void updateUnreadMessage(Message message) {
        chatDAO.updateUnreadMessage(message);
    }

}
