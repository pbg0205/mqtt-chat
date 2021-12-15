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
    private String name;

    @NotBlank
    private String password;

    @Email
    @NotBlank
    private String email;

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .password(password)
                .email(email)
                .build();
    }
}
