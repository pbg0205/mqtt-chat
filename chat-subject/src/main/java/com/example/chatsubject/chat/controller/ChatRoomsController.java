package com.example.chatsubject.chat.controller;

import com.example.chatsubject.chat.dto.ChatRoomDetailsResponse;
import com.example.chatsubject.chat.dto.ChatRoomLookupResponse;
import com.example.chatsubject.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomsController {

    private final ChatRoomService chatRoomService;
    private final AtomicInteger sequence = new AtomicInteger(0);

    @GetMapping
    public String getChatRoomListPage() {
        return "redirect:/chat/rooms";
    }

    @GetMapping(value = "/rooms")
    public String getRoomListPage(Model model) {
        List<ChatRoomLookupResponse> chatRoomList = chatRoomService.getChatRoomList();
        model.addAttribute("chatRoomList", chatRoomList);
        return "chatroom-main";
    }

    @GetMapping(value = "/rooms/{id}")
    public String joinRoom(@PathVariable String id, Model model) {
        ChatRoomDetailsResponse chatRoomDetailsResponse = chatRoomService.findById(id);
        model.addAttribute("room", chatRoomDetailsResponse);
        model.addAttribute("member", "member" + sequence.incrementAndGet());
        return "chatroom-detail";
    }

}
