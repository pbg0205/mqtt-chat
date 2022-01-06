package com.example.chatsubject.common.config;

import com.example.chatsubject.account.domain.Email;
import com.example.chatsubject.account.domain.User;
import com.example.chatsubject.account.domain.UserRepository;
import com.example.chatsubject.account.dto.UserDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class AuthProvider implements AuthenticationProvider {

    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = String.valueOf(authentication.getPrincipal());
        String password = String.valueOf(authentication.getCredentials());

        User user = userRepository.findByEmail(new Email(email))
                .orElseThrow(() -> new UsernameNotFoundException("사용자가 존재하지 않습니다."));

        if (matchPassword(password, user.getPassword())) {
            throw new BadCredentialsException("사용자의 정보가 일치하지 않습니다.");
        }

        UserDetailsResponse userDetailsResponse = UserDetailsResponse.from(user);

        return new UsernamePasswordAuthenticationToken(
                userDetailsResponse,
                null,
                Collections.singletonList(new SimpleGrantedAuthority(user.getAuthority()))
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private boolean matchPassword(String loginPassword, String password) {
        return password.equals(loginPassword);
    }
}
