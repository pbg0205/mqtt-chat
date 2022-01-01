package com.example.chatsubject.chat.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChatRoomRepository extends CrudRepository<ChatRoom, String> {

    List<ChatRoom> findAll();

}
