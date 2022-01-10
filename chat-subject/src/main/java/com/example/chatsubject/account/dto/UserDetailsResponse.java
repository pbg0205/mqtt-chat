package com.example.chatsubject.account.dto;

import com.example.chatsubject.account.domain.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode(of= {"email"})
public class UserDetailsResponse implements UserDetails {

    private final String email;
    private final String password;
    private final String nickname;
    private final List<GrantedAuthority> authorities;

    public static UserDetailsResponse from(User user) {
        return new UserDetailsResponse(
                user.getUsername(),
                user.getPassword(),
                user.getNickname(),
                (List<GrantedAuthority>) user.getAuthorities()
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
