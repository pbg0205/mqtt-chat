package com.example.chatsubject.chat.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor (access = AccessLevel.PRIVATE)
public class ChatRoomDetailsResponseDTO {

    private final Long id;
    private final String name;
    private final List<ChatMessageLookUpResponseDTO> chatMessageLookUpResponseList;

    public static ChatRoomDetailsResponseDTO fromEntity(Long chatRoomId,
                                                        String chatRoomName,
                                                        List<ChatMessageLookUpResponseDTO> responseList) {
        return new ChatRoomDetailsResponseDTO(chatRoomId, chatRoomName, responseList);
    }

}
