package com.example.chatsubject.chat.service;

import com.example.chatsubject.chat.domain.ChatMessage;
import com.example.chatsubject.chat.domain.ChatMessageRepository;
import com.example.chatsubject.chat.domain.ChatRoom;
import com.example.chatsubject.chat.domain.ChatRoomRepository;
import com.example.chatsubject.chat.dto.ChatMessageSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ChatMessageSaveService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;

    @Transactional
    public void save(ChatMessageSaveRequest saveRequest) {
        Optional<ChatRoom> chatRoomOptional = chatRoomRepository.findById(saveRequest.getChatRoomId());

        if (chatRoomOptional.isPresent()) {
            ChatRoom chatRoom = chatRoomOptional.get();
            ChatMessage chatMessage = saveRequest.toEntity();
            chatRoom.addChatMessage(chatMessage);
            chatMessageRepository.save(chatMessage);
        }
    }

}
