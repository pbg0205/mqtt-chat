package com.example.chatsubject.account.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void save_when_arguments_are_normal() {
        //given
        Member member = Member.builder()
                .name("cooper")
                .password("123")
                .email("cooper@rsupport.com")
                .build();

        //when
        Member savedMember = testEntityManager.persist(member);
        Optional<Member> foundMemberOptional = memberRepository.findById(member.getId());

        //then
        assertThat(savedMember).isEqualTo(foundMemberOptional.get());
    }
}
