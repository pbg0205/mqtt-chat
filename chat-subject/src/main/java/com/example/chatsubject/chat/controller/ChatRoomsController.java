package com.example.chatsubject.chat.controller;

import com.example.chatsubject.account.dto.UserDetailsResponse;
import com.example.chatsubject.chat.dto.ChatRoomDetailsResponse;
import com.example.chatsubject.chat.dto.ChatRoomLookupResponse;
import com.example.chatsubject.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomsController {

    private final ChatRoomService chatRoomService;

    @GetMapping(value = "/rooms")
    public String getChatRoomListPage(Model model) {
        List<ChatRoomLookupResponse> chatRoomList = chatRoomService.getChatRoomList();
        model.addAttribute("chatRoomList", chatRoomList);
        return "chatroom-main";
    }

    @GetMapping(value = "/rooms/{id}")
    public String joinRoom(@AuthenticationPrincipal UserDetailsResponse userDetailsResponse,
                           @PathVariable Long id,
                           Model model) {
        ChatRoomDetailsResponse chatRoomDetailsResponse = chatRoomService.getChatRoomDetails(id);
        model.addAttribute("room", chatRoomDetailsResponse);
        model.addAttribute("member", userDetailsResponse);
        return "chatroom-detail";
    }

}
