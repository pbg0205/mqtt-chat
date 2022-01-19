package com.example.chatsubject.chat.service;

import com.example.chatsubject.chat.domain.ChatRoom;
import com.example.chatsubject.chat.domain.ChatRoomRepository;
import com.example.chatsubject.chat.dto.ChatMessageLookUpResponse;
import com.example.chatsubject.chat.dto.ChatRoomDetailsResponse;
import com.example.chatsubject.chat.dto.ChatRoomLookupResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final MqttPahoMessageDrivenChannelAdapter mqttPahoMessageDrivenChannelAdapter;

    public List<ChatRoomLookupResponse> getChatRoomList() {
        List<ChatRoom> chatRoomList = chatRoomRepository.findAll();
        return chatRoomList.stream()
                .map(ChatRoomLookupResponse::from)
                .collect(Collectors.toList());
    }

    public ChatRoomDetailsResponse getChatRoomDetails(Long id) {
        ChatRoom chatRoom = chatRoomRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        List<ChatMessageLookUpResponse> chatMessageLookUpResponses = chatRoom.getChatMessages().stream()
                .map(ChatMessageLookUpResponse::fromEntity)
                .sorted(Comparator.comparing(ChatMessageLookUpResponse::getCreatedAt))
                .collect(Collectors.toList());
        return ChatRoomDetailsResponse.fromEntity(chatRoom.getId(), chatRoom.getName(), chatMessageLookUpResponses);
    }

    private void createChatRoom(String chatRoomName) {
        ChatRoom chatRoom = chatRoomRepository.save(new ChatRoom(chatRoomName));
        String topic = "topic" + chatRoom.getId();
        mqttPahoMessageDrivenChannelAdapter.addTopic(topic);
    }

    @PostConstruct
    private void postConstruct() {
        chatRoomRepository.deleteAll();
        createChatRoom("채팅방1");
        createChatRoom("채팅방2");
        createChatRoom("채팅방3");
    }

}
