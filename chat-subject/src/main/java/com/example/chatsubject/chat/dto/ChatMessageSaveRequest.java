package com.example.chatsubject.chat.dto;

import com.example.chatsubject.chat.domain.ChatMessage;
import com.example.chatsubject.chat.domain.MessageType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatMessageSaveRequest {

    private String writerName;

    private String message;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    private LocalDateTime createAt;

    private Long chatRoomId;

    public ChatMessage toEntity() {
        return ChatMessage.builder()
                .writerName(writerName)
                .message(message)
                .messageType(messageType)
                .createAt(createAt)
                .build();
    }

}
