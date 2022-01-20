package com.example.chatsubject.chat.controller;

import com.example.chatsubject.account.service.UserService;
import com.example.chatsubject.chat.service.ChatRoomService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("로그인하지 않은 사용자는 채팅방 리스트 페이지에 접근할 수 없다.")
    void can_not_render_chat_room_list_if_user_is_not_login() throws Exception {
        mockMvc.perform(get("/chat/rooms"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

}
