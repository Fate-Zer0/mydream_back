package com.example.mydream_back.socket;

import com.example.mydream_back.services.account.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserOnlineSocket {
    @Autowired
    private UserService userService;

    private final Map<String, Boolean> onlineUsers = new ConcurrentHashMap<>();

    // 用户上线
    public void userOnline(String user_id) {
        onlineUsers.put(user_id, true);
        userService.changerUserStatus(user_id,"20002");
    }

    // 用户下线
    public void userOffline(String user_id) {
        onlineUsers.remove(user_id);
        userService.changerUserStatus(user_id,"20001");
    }

    // 判断用户是否在线
    public boolean isUserOnline(String user_id) {
        return onlineUsers.containsKey(user_id);
    }

    // 获取所有在线用户
    public Map<String, Boolean> getOnlineUsers() {
        return onlineUsers;
    }

}
