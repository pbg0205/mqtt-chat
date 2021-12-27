package com.example.chatsubject.chat.domain;

import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@RedisHash("ChatContent")
public class ChatContent implements Serializable {

    @Id
    private Long id;

    private Long roomId;

    private String author;

    private String content;

    private LocalDateTime createdAt;

    public ChatContent(Long roomId, String author, String content, LocalDateTime createdAt) {
        this.roomId = roomId;
        this.author = author;
        this.content = content;
        this.createdAt = createdAt;
    }
}
