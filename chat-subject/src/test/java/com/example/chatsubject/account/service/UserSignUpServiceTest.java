package com.example.chatsubject.account.service;

import com.example.chatsubject.account.domain.User;
import com.example.chatsubject.account.domain.UserRepository;
import com.example.chatsubject.account.dto.UserSignUpRequestDTO;
import com.example.chatsubject.account.dto.UserSignUpResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {UserSignUpService.class, UserRepository.class})
class UserSignUpServiceTest {

    @Autowired
    UserSignUpService userSignUpService;

    @MockBean
    PasswordEncoder passwordEncoder;

    @MockBean
    UserRepository userRepository;

    @Test
    @DisplayName("적절한 입력 값일 경우, 정상 등록한다.")
    void create_member_when_input_is_correct() {
        //given
        UserSignUpRequestDTO request
                = new UserSignUpRequestDTO("sample", "123", "sample@rsupport.com");
        User user = request.toEntity();
        when(userRepository.save(any())).thenReturn(user);

        //when
        UserSignUpResponseDTO response = userSignUpService.signUpMember(request);

        //then
        assertThat(request.getNickname()).isEqualTo(response.getName());
        assertThat(request.getEmail()).isEqualTo(response.getEmail());
    }
}
