package com.example.chatsubject.account.service;


import com.example.chatsubject.account.domain.User;
import com.example.chatsubject.account.domain.UserRepository;
import com.example.chatsubject.account.dto.UserSignUpRequest;
import com.example.chatsubject.account.dto.UserSignUpResponse;
import com.example.chatsubject.account.exception.DuplicatedUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserSignUpResponse signUpMember(UserSignUpRequest userSignUpRequest) {
        User user = userSignUpRequest.toEntity();
        validateDuplicatedUser(userSignUpRequest);
        User savedUser = userRepository.save(user);
        return UserSignUpResponse.builder()
                .name(savedUser.getNickname())
                .email(savedUser.getEmail())
                .build();
    }

    private void validateDuplicatedUser(UserSignUpRequest userSignUpRequest) throws DuplicatedUserException {
        if (userRepository.existsByEmail(userSignUpRequest.getEmail())) {
            throw new DuplicatedUserException("기존 회원이 존재합니다.");
        }
    }
}
