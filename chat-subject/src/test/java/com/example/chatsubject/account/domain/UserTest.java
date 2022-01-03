package com.example.chatsubject.account.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    @DisplayName("이름과 비밀번호, 이메일를 정상 기재했을 경우, 멤버를 생성한다.")
    void createTest() {
        User user = User.builder()
                .password("123")
                .email("cooper@rsupport.com")
                .nickname("cooper")
                .build();

        assertThat(user.getNickname()).isEqualTo("cooper");
        assertThat(user.getPassword()).isEqualTo("123");
        assertThat(user.getEmail()).isEqualTo(new Email("cooper@rsupport.com"));
    }

}
