package com.example.chatsubject.chat.service;

import com.example.chatsubject.chat.domain.ChatRoom;
import com.example.chatsubject.chat.domain.ChatRoomRepository;
import com.example.chatsubject.chat.dto.ChatRoomDetailsResponse;
import com.example.chatsubject.chat.dto.ChatRoomLookupResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepositoryImpl;

    public List<ChatRoomLookupResponse> getChatRoomList() {
        List<ChatRoom> chatRoomList = chatRoomRepositoryImpl.findAll();
        return chatRoomList.stream()
                .map(ChatRoomLookupResponse::from)
                .collect(Collectors.toList());
    }

    public ChatRoomDetailsResponse findById(String id) {
        ChatRoom chatRoom = chatRoomRepositoryImpl.findById(id).orElseThrow(IllegalArgumentException::new);
        return ChatRoomDetailsResponse.from(chatRoom);
    }

    @PostConstruct
    private void postConstruct() {
        chatRoomRepositoryImpl.deleteAll();
        chatRoomRepositoryImpl.save(new ChatRoom("채팅방1"));
        chatRoomRepositoryImpl.save(new ChatRoom("채팅방2"));
        chatRoomRepositoryImpl.save(new ChatRoom("채팅방3"));
    }

}
