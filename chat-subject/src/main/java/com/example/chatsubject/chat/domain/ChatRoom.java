package com.example.chatsubject.chat.domain;

import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Getter
@RedisHash("ChatRoom")
public class ChatRoom {

    @Id
    private String id;

    private final String name;

    public ChatRoom(String name) {
        this.name = name;
    }

}