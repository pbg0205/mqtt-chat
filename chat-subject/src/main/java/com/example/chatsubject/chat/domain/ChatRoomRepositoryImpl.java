package com.example.chatsubject.chat.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ChatRoomRepositoryImpl implements ChatRoomRepository {

    private final Map<String, ChatRoom> repository = new HashMap<>();

    @Override
    public List<ChatRoom> findAll() {
        return new ArrayList<>(repository.values());
    }

    @Override
    public Optional<ChatRoom> findById(String id) {
        ChatRoom chatRoom = repository.get(id);
        return Optional.ofNullable(chatRoom);
    }

    @Override
    public void deleteAll() {
        repository.clear();
    }

    @Override
    public ChatRoom save(ChatRoom chatRoom) {
        return repository.put(chatRoom.getId(), chatRoom);
    }

}
