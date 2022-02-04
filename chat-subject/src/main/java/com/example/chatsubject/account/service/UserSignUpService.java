package com.example.chatsubject.account.service;


import com.example.chatsubject.account.domain.Email;
import com.example.chatsubject.account.domain.User;
import com.example.chatsubject.account.domain.UserRepository;
import com.example.chatsubject.account.dto.UserSignUpRequestDTO;
import com.example.chatsubject.account.dto.UserSignUpResponseDTO;
import com.example.chatsubject.account.exception.DuplicatedUserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserSignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserSignUpResponseDTO signUpMember(UserSignUpRequestDTO userSignUpRequestDTO) throws DuplicatedUserException {
        User user = userSignUpRequestDTO.toEntity();
        user.encodePassword(passwordEncoder);

        validateDuplicatedUser(userSignUpRequestDTO);

        User savedUser = userRepository.save(user);
        log.debug("{}", savedUser);
        return UserSignUpResponseDTO.builder()
                .name(savedUser.getNickname())
                .email(savedUser.getEmail().getEmail())
                .build();
    }

    private void validateDuplicatedUser(UserSignUpRequestDTO userSignUpRequestDTO) {
        if (userRepository.existsByEmail(new Email(userSignUpRequestDTO.getEmail()))) {
            throw new DuplicatedUserException("기존 회원이 존재합니다.");
        }
    }

}
