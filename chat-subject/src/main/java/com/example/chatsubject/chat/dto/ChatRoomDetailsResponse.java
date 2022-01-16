package com.example.chatsubject.chat.dto;

import com.example.chatsubject.chat.domain.ChatRoom;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ChatRoomDetailsResponse {

    private final Long id;
    private final String name;
    private final List<ChatMessageLookUpResponse> chatMessageLookUpResponseList;

    public static ChatRoomDetailsResponse from(ChatRoom chatRoom, List<ChatMessageLookUpResponse> responseList) {
        return new ChatRoomDetailsResponse(chatRoom.getId(), chatRoom.getName(), responseList);
    }

}
