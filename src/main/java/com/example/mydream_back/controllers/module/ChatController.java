package com.example.mydream_back.controllers.module;

import com.example.mydream_back.dto.ChatInfo;
import com.example.mydream_back.dto.Message;
import com.example.mydream_back.dto.ReturnValue;
import com.example.mydream_back.services.model.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/module/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/getChatMassageList")
    public ResponseEntity<ReturnValue<List<Message>>> getChatMassageList(@RequestParam String send_id, String receive_id, String msg_state){
        ReturnValue<List<Message>> ret = new ReturnValue<>();
        List<Message> messageList = chatService.getChatMessageList(send_id,receive_id,msg_state);
        ret.setRetValue(messageList);
        ret.isSuccess();
        return ResponseEntity.ok(ret);
    }
    @GetMapping("/getChatMessageListByUseridAndFriendid")
    public ResponseEntity<ReturnValue<List<Message>>> getChatMessageListByUseridAndFriendid(@RequestParam String user_id, String friend_id){
        ReturnValue<List<Message>> ret = new ReturnValue<>();
        List<Message> messageList = chatService.getChatMessageListBySendidAndReceiceid(user_id,friend_id);
        ret.setRetValue(messageList);
        ret.isSuccess();
        return ResponseEntity.ok(ret);
    }
    @PostMapping("/addChatMassage")
    public ResponseEntity<ReturnValue<String>> addChatMassage(@RequestBody Message message){
        ReturnValue<String> ret = new ReturnValue<>();
        chatService.addChatMessage(message);
        ret.isSuccess();
        return ResponseEntity.ok(ret);
    }

    @GetMapping("getChatInfoList")
    public ResponseEntity<ReturnValue<List<ChatInfo>>> getChatInfoList(@RequestParam String user_id){
        ReturnValue<List<ChatInfo>> ret = new ReturnValue<>();
        ret.setRetValue(chatService.getChatInfoList(user_id));
        ret.isSuccess();
        return ResponseEntity.ok(ret);
    }


    @GetMapping("getLastMessage")
    public ResponseEntity<ReturnValue<Message>> getLastMessage(@RequestParam String user_id, String friend_id){
        ReturnValue<Message> ret = new ReturnValue<>();
        ret.setRetValue(chatService.getLastMessage(user_id,friend_id));
        ret.isSuccess();
        return ResponseEntity.ok(ret);
    }

    /**
     * 处理前端发送的 /app/chat 请求
     */
    @MessageMapping("/chat") // 对应前端 send("/app/chat", ...)
    public void handleChat(Map<String, Object> payload, StompHeaderAccessor headerAccessor) {
        // 获取发送者（从握手阶段的 Principal）
        String senderId = headerAccessor.getUser().getName();

        // 解析消息内容
        String type = (String) payload.get("type");
        String receiverId = (String) payload.get("to"); // 假设前端传了接收者 ID

        // ✅ 发送私信给接收者
        if (receiverId != null) {
            messagingTemplate.convertAndSendToUser(
                    receiverId,           // 接收者 user_id
                    "/queue/private",     // 前端订阅的路径
                    Map.of(
                            "from", senderId,
                            "type", type,
                            "timestamp", System.currentTimeMillis()
                    )
            );
        }
    }
}
