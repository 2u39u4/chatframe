package com.example.chatframe.controller;

import com.example.chatframe.model.chatmessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class chatcontroller {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public chatmessage sendMessage(@Payload chatmessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public chatmessage addUser(@Payload chatmessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // 将用户名添加到WebSocket会话
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}