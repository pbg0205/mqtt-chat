package com.example.chatsubject.account.dto;

import com.example.chatsubject.account.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpRequest {

    @NotBlank(message = "{notnull.error.message}")
    private String nickname;

    @NotBlank(message = "{notnull.error.message}")
    private String password;

    @Email(message = "{format.error.message}")
    @NotBlank(message = "{notnull.error.message}")
    private String email;

    public User toEntity() {
        return User.builder()
                .password(password)
                .email(email)
                .nickname(nickname)
                .build();
    }
}
