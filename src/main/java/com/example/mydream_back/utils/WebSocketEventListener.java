package com.example.mydream_back.utils;

import com.example.mydream_back.socket.UserOnlineSocket;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.security.Principal;

@Component
public class WebSocketEventListener {

    private final UserOnlineSocket userOnlineSocket;

    public WebSocketEventListener(UserOnlineSocket userOnlineSocket) {
        this.userOnlineSocket = userOnlineSocket;
    }

    @EventListener
    public void handleWebSocketConnect(SessionConnectedEvent event) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(event.getMessage());

        Principal principal = headerAccessor.getUser();
        if (principal == null) {
            System.out.println("无法识别的连接请求，Principal 为空");
            return;
        }

        String user_id = principal.getName();

        userOnlineSocket.userOnline(user_id);
    }


    @EventListener
    public void handleWebSocketDisconnect(SessionDisconnectEvent event) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(event.getMessage());

        Principal principal = headerAccessor.getUser();

        if (principal != null) {
            String user_id = principal.getName();
            userOnlineSocket.userOffline(user_id);
        }
    }
}