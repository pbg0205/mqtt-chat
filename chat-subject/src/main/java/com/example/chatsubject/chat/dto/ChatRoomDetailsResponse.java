package com.example.chatsubject.chat.dto;

import com.example.chatsubject.chat.domain.ChatRoom;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ChatRoomDetailsResponse {

    private final Long id;
    private final String name;

    public static ChatRoomDetailsResponse from(ChatRoom chatRoom) {
        return new ChatRoomDetailsResponse(chatRoom.getId(), chatRoom.getName());
    }
}
