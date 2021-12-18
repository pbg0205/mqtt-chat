package com.example.chatsubject.account.controller;

import com.example.chatsubject.account.dto.UserSignUpRequest;
import com.example.chatsubject.account.dto.UserSignUpResponse;
import com.example.chatsubject.account.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("회원가입 정상 동작을 테스트한다.")
    void should_return_sign_up_result() throws Exception {
        //given
        UserSignUpRequest request = new UserSignUpRequest("cooper", "123", "cooper@rsupport.com");
        UserSignUpResponse response = new UserSignUpResponse("cooper", "cooper@rsupport.com");

        when(userService.signUpMember(request)).thenReturn(response);

        //then
        mockMvc.perform(post("/signup")
                        .param("name", "cooper")
                        .param("password", "123")
                        .param("email", "cooper@rsupport.com"))
                .andExpect(status().isCreated())
                .andExpect(view().name("index"));
    }
}
