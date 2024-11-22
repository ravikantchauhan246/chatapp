package com.ravikant.chatapp.listener;

import com.ravikant.chatapp.dto.ChatMessage;
import com.ravikant.chatapp.dto.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j

public class WebSocketEventListener {
    private final RedisTemplate<String, Object> redisTemplate;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {

        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");

        if(username != null) {
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setMessageType(MessageType.LEAVE);
            chatMessage.setUserName(username);
            chatMessage.setMessage(username + " left the chat");
            log.info("User disconnected: {}",username);
            redisTemplate.convertAndSend("chat",chatMessage);


        }

    }


}
