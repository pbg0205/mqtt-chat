package com.example.chatsubject.chat.domain;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ChatRoom {

    private final String id;

    private final String name;

    public ChatRoom(String name) {
        this.id = generateId();
        this.name = name;
    }

    private String generateId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

}
