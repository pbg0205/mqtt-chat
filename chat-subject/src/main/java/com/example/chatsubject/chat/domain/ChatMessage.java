package com.example.chatsubject.chat.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

    private String chatRoomId;
    private String writer;
    private String message;
    private MessageType messageType;

}
