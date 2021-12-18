package com.example.chatsubject.account.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    void save_when_arguments_are_normal() {
        //given
        User user = User.builder()
                .email("cooper@rsupport.com")
                .password("123")
                .nickname("cooper")
                .build();

        //when
        User savedUser = testEntityManager.persist(user);
        Optional<User> foundMemberOptional = userRepository.findById(user.getId());

        //then
        assertThat(savedUser).isEqualTo(foundMemberOptional.get());
    }
}
