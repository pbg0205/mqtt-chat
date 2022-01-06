package com.example.chatsubject.chat.domain;


import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository {

    List<ChatRoom> findAll();

    Optional<ChatRoom> findById(String id);

    void deleteAll();

    ChatRoom save(ChatRoom chatRoom);
}
