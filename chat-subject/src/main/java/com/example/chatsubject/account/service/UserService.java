package com.example.chatsubject.account.service;


import com.example.chatsubject.account.domain.Email;
import com.example.chatsubject.account.domain.User;
import com.example.chatsubject.account.domain.UserRepository;
import com.example.chatsubject.account.dto.UserSignUpRequest;
import com.example.chatsubject.account.dto.UserSignUpResponse;
import com.example.chatsubject.account.exception.DuplicatedUserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserSignUpResponse signUpMember(UserSignUpRequest userSignUpRequest) throws DuplicatedUserException {
        User user = userSignUpRequest.toEntity();
        user.encodePassword(passwordEncoder);

        validateDuplicatedUser(userSignUpRequest);

        User savedUser = userRepository.save(user);
        log.debug("{}", savedUser);
        return UserSignUpResponse.builder()
                .name(savedUser.getNickname())
                .email(savedUser.getEmail().getEmail())
                .build();
    }

    private void validateDuplicatedUser(UserSignUpRequest userSignUpRequest) {
        if (userRepository.existsByEmail(new Email(userSignUpRequest.getEmail()))) {
            throw new DuplicatedUserException("기존 회원이 존재합니다.");
        }
    }

}
