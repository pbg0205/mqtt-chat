package com.example.chatsubject.chat.dto;

import com.example.chatsubject.chat.domain.ChatRoom;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ChatRoomLookupResponse {

    private final String roomId;
    private final String roomName;

    public static ChatRoomLookupResponse from(ChatRoom chatRoom) {
        return new ChatRoomLookupResponse(chatRoom.getId(), chatRoom.getName());
    }
}