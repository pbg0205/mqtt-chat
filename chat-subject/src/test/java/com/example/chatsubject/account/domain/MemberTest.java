package com.example.chatsubject.account.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    @DisplayName("이름과 비밀번호를 기재했을 경우, 멤버를 생성된다.")
    void createTest() {
        Member member = new Member("cooper", "123");
        assertThat(member.getName()).isEqualTo("cooper");
        assertThat(member.getPassword()).isEqualTo("123");
    }

    @Test
    @DisplayName("이름을 기재하지 않을 경우, IllegalArgumentException을 던진다.")
    void retrieve_illegal_argument_exception_when_name_is_empty() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Member("", "123"),
                "이름이 존재하지 않습니다."
        );
    }

    @Test
    @DisplayName("비밀번호를 기재하지 않을 경우, IllegalArgumentException을 던진다.")
    void retrieve_illegal_argument_exception_when_password_is_empty() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Member("cooper", ""),
                "비밀번호가 존재하지 않습니다."
        );
    }
}
