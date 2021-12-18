package com.example.chatsubject.account.dto;

import com.example.chatsubject.account.domain.Member;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberSignUpRequest {

    @NotBlank
    private String nickname;

    @NotBlank
    private String password;

    @Email
    @NotBlank
    private String email;

    public Member toEntity() {
        return Member.builder()
                .password(password)
                .email(email)
                .nickname(nickname)
                .build();
    }
}
