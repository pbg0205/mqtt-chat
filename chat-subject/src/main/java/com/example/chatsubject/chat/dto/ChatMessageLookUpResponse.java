package com.example.chatsubject.chat.dto;

import com.example.chatsubject.chat.domain.ChatMessage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor (access = AccessLevel.PRIVATE)
public class ChatMessageLookUpResponse {

    private final Long id;
    private final String message;
    private final String writerName;
    private final LocalDateTime createdAt;

    public static ChatMessageLookUpResponse fromEntity(ChatMessage chatMessage) {
        return new ChatMessageLookUpResponse(
                chatMessage.getId(),
                chatMessage.getMessage(),
                chatMessage.getWriterName(),
                chatMessage.getCreateAt()
        );
    }

}
