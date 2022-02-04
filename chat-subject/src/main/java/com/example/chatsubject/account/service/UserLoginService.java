package com.example.chatsubject.account.service;

import com.example.chatsubject.account.domain.Email;
import com.example.chatsubject.account.domain.User;
import com.example.chatsubject.account.domain.UserRepository;
import com.example.chatsubject.account.dto.UserDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(new Email(username))
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 사용자 입니다."));
        return UserDetailsDTO.from(user);
    }

}
