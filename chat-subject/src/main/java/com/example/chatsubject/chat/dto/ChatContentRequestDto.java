package com.example.chatsubject.chat.dto;

import com.example.chatsubject.chat.domain.ChatContent;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class ChatContentRequestDto {

    private Long roomId;

    private String author;

    private String content;

    private LocalDateTime createdTime;

    public ChatContent toEntity() {
        return new ChatContent(roomId, author, content, createdTime);
    }
}
