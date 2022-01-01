package com.example.chatsubject.chat.controller;

import com.example.chatsubject.account.dto.UserDetailsResponse;
import com.example.chatsubject.account.service.UserService;
import com.example.chatsubject.chat.dto.ChatRoomDetailsResponse;
import com.example.chatsubject.chat.dto.ChatRoomLookupResponse;
import com.example.chatsubject.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomsController {

    private final ChatRoomService chatRoomService;
    private final UserService userService;

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
    public String joinRoom(@PathVariable String id, Model model, Principal principal) {
        ChatRoomDetailsResponse chatRoomDetailsResponse = chatRoomService.findById(id);
        model.addAttribute("room", chatRoomDetailsResponse);

        UserDetailsResponse userDetailsResponse = userService.findByEmail(principal.getName());
        model.addAttribute("member", userDetailsResponse.getNickname());
        return "chatroom-detail";
    }

}
