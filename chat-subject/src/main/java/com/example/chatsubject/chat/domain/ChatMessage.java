package com.example.chatsubject.chat.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@Table(name = "chat_message")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage {

    @Id
    @Column(name = "chat_message_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chat_message_message")
    private String message;

    @Column(name = "chat_message_writer_id")
    private String writerName;

    @Enumerated(EnumType.STRING)
    @Column(name = "chat_message_message_type")
    private MessageType messageType;

    @Column(name = "chat_message_created_at")
    private LocalDateTime createAt;

    @JoinColumn(name="chat_room_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ChatRoom chatRoom;

    @Builder
    public ChatMessage(String writerName,
                       String message,
                       MessageType messageType,
                       LocalDateTime createAt) {
        this.writerName = writerName;
        this.message = message;
        this.messageType = messageType;
        this.createAt = createAt;
    }

    void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

}
