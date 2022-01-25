package com.example.chatsubject.chat.service;

import com.example.chatsubject.chat.domain.ChatMessage;
import com.example.chatsubject.chat.domain.ChatRoom;
import com.example.chatsubject.chat.domain.ChatRoomRepository;
import com.example.chatsubject.chat.dto.ChatMessageLookUpResponseDTO;
import com.example.chatsubject.chat.dto.ChatRoomDetailsResponseDTO;
import com.example.chatsubject.chat.dto.ChatRoomLookupResponseDTO;
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

    public List<ChatRoomLookupResponseDTO> getChatRoomList() {
        List<ChatRoom> chatRoomList = chatRoomRepository.findAll();
        return chatRoomList.stream()
                .map(ChatRoomLookupResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public ChatRoomDetailsResponseDTO getChatRoomDetails(Long id) {
        ChatRoom chatRoom = chatRoomRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        List<ChatMessageLookUpResponseDTO> chatMessageLookUpResponses
                = getMessageLookUpResponseListOrderByCreateAt(chatRoom.getChatMessages());
        return ChatRoomDetailsResponseDTO.fromEntity(chatRoom.getId(), chatRoom.getName(), chatMessageLookUpResponses);
    }

    private List<ChatMessageLookUpResponseDTO> getMessageLookUpResponseListOrderByCreateAt(List<ChatMessage> chatMessageList) {
        return chatMessageList.stream()
                .map(ChatMessageLookUpResponseDTO::fromEntity)
                .sorted(Comparator.comparing(ChatMessageLookUpResponseDTO::getCreatedAt))
                .collect(Collectors.toList());
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
