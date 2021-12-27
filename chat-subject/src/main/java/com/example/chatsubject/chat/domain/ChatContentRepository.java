package com.example.chatsubject.chat.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatContentRepository extends CrudRepository <ChatContent, Long> {
}
