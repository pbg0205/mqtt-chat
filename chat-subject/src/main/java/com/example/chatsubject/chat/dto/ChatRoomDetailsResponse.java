package com.example.chatsubject.chat.dto;

import com.example.chatsubject.chat.domain.ChatRoom;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor (access = AccessLevel.PRIVATE)
public class ChatRoomDetailsResponse {

    private final Long id;
    private final String name;
    private final List<ChatMessageLookUpResponse> chatMessageLookUpResponseList;

    public static ChatRoomDetailsResponse fromEntity(Long chatRoomId,
                                                     String chatRoomName,
                                                     List<ChatMessageLookUpResponse> responseList) {
        return new ChatRoomDetailsResponse(chatRoomId, chatRoomName, responseList);
    }

}
