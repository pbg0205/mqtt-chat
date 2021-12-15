package com.example.chatsubject.account.dto;

import com.example.chatsubject.account.domain.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class MemberSignUpRequest {

    @NotBlank
    private final String name;

    @NotBlank
    private final String password;

    @Email
    @NotBlank
    private final String email;

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .password(password)
                .email(email)
                .build();
    }
}
