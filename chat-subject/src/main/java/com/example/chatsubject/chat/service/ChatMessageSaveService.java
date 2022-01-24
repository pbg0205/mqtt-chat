package com.example.chatsubject.chat.service;

import com.example.chatsubject.chat.domain.ChatMessage;
import com.example.chatsubject.chat.domain.ChatMessageRepository;
import com.example.chatsubject.chat.domain.ChatRoomRepository;
import com.example.chatsubject.chat.dto.ChatMessageSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ChatMessageSaveService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;

    @Transactional
    public void saveChatMessage(ChatMessageSaveRequest saveRequest) {
        chatRoomRepository.findById(saveRequest.getChatRoomId())
                .ifPresent(chatRoom -> {
                    ChatMessage chatMessage = saveRequest.toEntity();
                    chatRoom.addChatMessage(chatMessage);
                    chatMessageRepository.save(chatMessage);
                });
    }

}
