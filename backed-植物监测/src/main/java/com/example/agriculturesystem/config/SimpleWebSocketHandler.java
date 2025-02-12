package com.example.agriculturesystem.config;

import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.TextMessage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class SimpleWebSocketHandler extends TextWebSocketHandler {

    private Set<WebSocketSession> sessions = new HashSet<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);  // 客户端连接时，加入 session 集合
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        sessions.remove(session);  // 客户端断开连接时，移除 session
    }

    // 发送数据到所有连接的客户端
    public void sendDataToClients(String message) {
        for (WebSocketSession session : sessions) {
            try {
                session.sendMessage(new TextMessage(message));  // 发送消息
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
