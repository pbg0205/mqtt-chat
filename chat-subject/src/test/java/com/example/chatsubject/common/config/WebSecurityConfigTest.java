package com.example.chatsubject.common.config;

import com.example.chatsubject.account.domain.User;
import com.example.chatsubject.account.dto.UserDetailsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;


@SpringBootTest
class WebSecurityConfigTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    @DisplayName("로그인 테스트")
    void loginTest() throws Exception {
        User user = User.builder()
                .nickname("cooper")
                .email("sample@sample.com")
                .password("password")
                .build();

        mvc.perform(post("/login")
                        .with(user(UserDetailsResponse.from(user)))
                        .param("username", "sample@sample.com")
                        .param("password", "password")
                ).andDo(print())
                .andExpect(authenticated());
    }

    @Test
    @DisplayName("로그아웃 테스트")
    void logoutTest() throws Exception {
        mvc.perform(post("/logout").with(csrf()))
                .andDo(print())
                .andExpect(unauthenticated())
                .andExpect(redirectedUrl("/"));
    }

}
