package com.example.chatsubject.chat.service;

import com.example.chatsubject.chat.domain.ChatRoom;
import com.example.chatsubject.chat.domain.ChatRoomRepository;
import com.example.chatsubject.chat.dto.ChatRoomLookupResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public List<ChatRoomLookupResponse> getChatRoomList() {
        List<ChatRoom> chatRoomList = chatRoomRepository.findAll();
        return chatRoomList.stream()
                .map(ChatRoomLookupResponse::from)
                .collect(Collectors.toList());
    }

    @PostConstruct
    private void postConstruct() {
        chatRoomRepository.deleteAll();
        chatRoomRepository.save(new ChatRoom("채팅방1"));
        chatRoomRepository.save(new ChatRoom("채팅방2"));
        chatRoomRepository.save(new ChatRoom("채팅방3"));
    }

}
