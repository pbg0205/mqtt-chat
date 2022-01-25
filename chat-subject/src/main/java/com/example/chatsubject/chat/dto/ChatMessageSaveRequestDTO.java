package com.example.chatsubject.chat.dto;

import com.example.chatsubject.chat.domain.ChatMessage;
import com.example.chatsubject.chat.domain.MessageType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatMessageSaveRequestDTO {

    private String writerName;

    private String message;

    private MessageType messageType;

    private LocalDateTime createdAt;

    private Long chatRoomId;

    public ChatMessage toEntity() {
        return ChatMessage.builder()
                .writerName(writerName)
                .message(message)
                .messageType(messageType)
                .createAt(createdAt)
                .build();
    }

}
