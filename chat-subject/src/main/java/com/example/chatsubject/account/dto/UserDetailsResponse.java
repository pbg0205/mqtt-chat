package com.example.chatsubject.account.dto;

import com.example.chatsubject.account.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserDetailsResponse {

    private final String email;
    private final String nickname;

    public static UserDetailsResponse from(User user) {
        return new UserDetailsResponse(user.getEmail().toString(), user.getNickname());
    }
}
