package com.example.chatsubject.chat.service;

import com.example.chatsubject.chat.domain.ChatMessage;
import com.example.chatsubject.chat.domain.ChatRoom;
import com.example.chatsubject.chat.domain.ChatRoomRepository;
import com.example.chatsubject.chat.dto.ChatMessageLookUpResponseDTO;
import com.example.chatsubject.chat.dto.ChatRoomDetailsResponseDTO;
import com.example.chatsubject.chat.dto.ChatRoomLookupResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomLookUpService {

    private final ChatRoomRepository chatRoomRepository;

    public List<ChatRoomLookupResponseDTO> getChatRoomList() {
        List<ChatRoom> chatRoomList = chatRoomRepository.findAll();
        return chatRoomList.stream()
                .map(ChatRoomLookupResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public ChatRoomDetailsResponseDTO getChatRoomDetails(Long id) {
        ChatRoom chatRoom = chatRoomRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        List<ChatMessageLookUpResponseDTO> chatMessageLookUpResponses
                = getMessageLookUpResponseList(chatRoom.getChatMessages());
        return ChatRoomDetailsResponseDTO.fromEntity(chatRoom.getId(), chatRoom.getName(), chatMessageLookUpResponses);
    }

    private List<ChatMessageLookUpResponseDTO> getMessageLookUpResponseList(List<ChatMessage> chatMessageList) {
        return chatMessageList.stream()
                .map(ChatMessageLookUpResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

}
