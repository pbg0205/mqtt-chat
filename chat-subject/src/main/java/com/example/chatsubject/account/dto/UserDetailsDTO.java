package com.example.chatsubject.account.dto;

import com.example.chatsubject.account.domain.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode(of= {"email"})
public class UserDetailsDTO implements UserDetails {

    private final String email;
    private final String password;
    private final String nickname;
    private final List<GrantedAuthority> authorities;

    public static UserDetailsDTO from(User user) {
        return new UserDetailsDTO(
                user.getEmail().getEmail(),
                user.getPassword(),
                user.getNickname(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getAuthority()))
        );
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
