package com.example.chatsubject.account.controller;

import com.example.chatsubject.account.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;


    @Test
    @DisplayName("로그인 페이지를 렌더링한다.")
    void should_render_login_page() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    @DisplayName("회원가입 페이지를 렌더링한다.")
    void should_render_signup_page() throws Exception {
        mockMvc.perform(get("/signup"))
                .andExpect(status().isOk())
                .andExpect(view().name("signup"))
                .andExpect(model().attributeExists("userSignUpRequest"));
    }

    @ParameterizedTest
    @MethodSource("provide_sign_up_input_for_value_is_null_or_blank")
    @DisplayName("회원가입 입력 값을 입력하지 않을 경우, 회원가입 페이지를 렌더링한다.")
    void should_render_sign_up_page_when_input_value_is_blank_and_null(String nickname,
                                                                       String password,
                                                                       String email) throws Exception {
        mockMvc.perform(post("/signup")
                        .param("nickname", nickname)
                        .param("password", password)
                        .param("email", email)
                        .with(csrf())
                ).andDo(print())
                .andExpect(view().name("signup"));
    }

    private static Stream<Arguments> provide_sign_up_input_for_value_is_null_or_blank() {
        return Stream.of(
                Arguments.of("", "password", "sample@sample.com"),
                Arguments.of("nickname", "", "sample@sample.com"),
                Arguments.of("nickname", "password", "")
        );
    }

    @Test
    @DisplayName("회원가입 성공 시, 로그인 페이지 페이지를 렌더링 한다.")
    void should_render_login_page_when_registration_is_succeed () throws Exception {
        mockMvc.perform(post("/signup")
                        .param("nickname", "cooper")
                        .param("password", "123")
                        .param("email", "bgpar@rsupport.com")
                        .with(csrf())
                ).andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

}
