package com.example.chatsubject.chat.controller;

import com.example.chatsubject.account.domain.User;
import com.example.chatsubject.account.dto.UserDetailsDTO;
import com.example.chatsubject.account.service.UserService;
import com.example.chatsubject.chat.dto.ChatRoomDetailsResponseDTO;
import com.example.chatsubject.chat.service.ChatRoomService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@WebMvcTest
class ChatRoomsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChatRoomService chatRoomService;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("로그인한 사용자는 채팅방 리스트 페이지에 접근할 수 있다.")
    @WithMockUser(username = "sample@sample.com")
    void can_render_chat_room_list_page_if_user_is_login() throws Exception {
        mockMvc.perform(get("/chat/rooms"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("로그인하지 않은 사용자는 채팅방 리스트 페이지에 접근할 수 없다.")
    void can_not_render_chat_room_list_if_user_is_not_login() throws Exception {
        mockMvc.perform(get("/chat/rooms"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @DisplayName("로그인한 사용자는 특정 채팅방에 입장할 수 있다.")
    void can_join_chat_room_when_user_is_login() throws Exception {
        UserDetailsDTO userDetailsDTO = UserDetailsDTO.from(User.builder()
                .email("sample@sample.com")
                .nickname("sample")
                .password("password")
                .build()
        );

        when(chatRoomService.getChatRoomDetails(1L)).thenReturn(
                ChatRoomDetailsResponseDTO.fromEntity(1L, "채팅방1", new ArrayList<>())
        );

        mockMvc.perform(get("/chat/rooms/1")
                        .with(user(userDetailsDTO)))
                .andExpect(status().isOk())
                .andExpect(view().name("chatroom-detail"));
    }

    @Test
    @DisplayName("로그인하지 않는 사용자는 특정 채팅방에 입장할 수 없다.")
    void can_not_join_room_when_user_is_not_authenticated() throws Exception {
        mockMvc.perform(get("/chat/rooms/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }
}
