package com.example.chatsubject.account.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    @DisplayName("이름과 비밀번호, 이메일를 정상 기재했을 경우, 멤버를 생성한다.")
    void createTest() {
        Member member = Member.builder()
                .name("cooper")
                .password("123")
                .email("cooper@rsupport.com")
                .build();

        assertThat(member.getName()).isEqualTo("cooper");
        assertThat(member.getPassword()).isEqualTo("123");
        assertThat(member.getEmail()).isEqualTo("cooper@rsupport.com");
    }

    @Test
    @DisplayName("이름을 기재하지 않을 경우, IllegalArgumentException을 던진다.")
    void retrieve_illegal_argument_exception_when_name_is_empty() {
        assertThrows(
                IllegalArgumentException.class,
                () -> Member.builder()
                        .name("")
                        .password("123")
                        .email("cooper@rsupport.com")
                        .build(),
                "이름이 존재하지 않습니다."
        );
    }

    @Test
    @DisplayName("비밀번호를 기재하지 않을 경우, IllegalArgumentException을 던진다.")
    void retrieve_illegal_argument_exception_when_password_is_empty() {
        assertThrows(
                IllegalArgumentException.class,
                () -> Member.builder()
                        .name("cooper")
                        .password("")
                        .email("123@rsupport.com")
                        .build(),
                "비밀번호가 존재하지 않습니다."
        );
    }

    @Test
    @DisplayName("올바른 이메일 형태가 아닐 경우, IllegalArgumentException을 반환한다.")
    void retrieve_illegal_argument_exception_when_input_is_not_email_format() {
        assertThrows(
                IllegalArgumentException.class,
                () -> Member.builder()
                        .name("cooper")
                        .password("123")
                        .email("coo@.com")
                        .build(),
                "이메일이 올바르지 않습니다."
        );
    }
}
