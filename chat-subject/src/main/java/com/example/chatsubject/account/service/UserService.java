package com.example.chatsubject.account.service;


import com.example.chatsubject.account.domain.Email;
import com.example.chatsubject.account.domain.User;
import com.example.chatsubject.account.domain.UserRepository;
import com.example.chatsubject.account.dto.UserSignUpRequest;
import com.example.chatsubject.account.dto.UserSignUpResponse;
import com.example.chatsubject.account.exception.DuplicatedUserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(new Email(username))
                .orElseThrow(() -> new UsernameNotFoundException("사용자의 아이디 혹은 비밀번호가 일치하지 않습니다."));
        log.debug("{}", user);
        return user;
    }
}
