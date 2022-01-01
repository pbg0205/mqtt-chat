package com.example.chatsubject.chat.domain;

import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.web.socket.WebSocketSession;

import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Getter
@RedisHash("ChatRoom")
public class ChatRoom {

    @Id
    private String id;

    private final String name;

    private final Set<WebSocketSession> sessions = new HashSet<>();

    public ChatRoom(String name) {
        this.name = name;
    }

}