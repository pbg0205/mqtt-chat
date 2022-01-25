package com.example.chatsubject.chat.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private String writerName;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    private LocalDateTime createdAt;

    @JoinColumn(name="chat_room_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ChatRoom chatRoom;

    @Builder
    public ChatMessage(String writerName,
                       String message,
                       MessageType messageType,
                       LocalDateTime createdAt) {
        this.writerName = writerName;
        this.message = message;
        this.messageType = messageType;
        this.createdAt = createdAt;
    }

    void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

}
