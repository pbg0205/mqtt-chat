package com.example.chatsubject.chat.service;

import com.example.chatsubject.chat.domain.ChatContentRepository;
import com.example.chatsubject.chat.dto.ChatContentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatContentService {

    private final ChatContentRepository chatContentRepository;

    public void save (ChatContentRequestDto requestDto) {
        chatContentRepository.save(requestDto.toEntity());
    }
}
