package com.ravikant.chatapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    private String message;
    private String timestamp;
    private String userName;
    private MessageType messageType;
}
