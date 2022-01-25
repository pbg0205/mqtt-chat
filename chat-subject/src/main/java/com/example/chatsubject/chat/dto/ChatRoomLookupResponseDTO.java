package com.example.chatsubject.chat.dto;

import com.example.chatsubject.chat.domain.ChatRoom;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor (access = AccessLevel.PRIVATE)
public class ChatRoomLookupResponseDTO {

    private final Long roomId;
    private final String roomName;

    public static ChatRoomLookupResponseDTO fromEntity(ChatRoom chatRoom) {
        return new ChatRoomLookupResponseDTO(chatRoom.getId(), chatRoom.getName());
    }
}
