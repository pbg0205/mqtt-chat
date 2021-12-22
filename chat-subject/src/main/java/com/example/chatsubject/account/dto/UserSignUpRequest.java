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

    @NotBlank(message = "닉네임은 필수값 입니다.")
    private String nickname;

    @NotBlank(message = "비밀번호는 필수값 입니다.")
    private String password;

    @Email(message = "이메일 형식으로 입력 해주세요.")
    @NotBlank(message = "이메일는 필수값 입니다.")
    private String email;

    public User toEntity() {
        return User.builder()
                .password(password)
                .email(email)
                .nickname(nickname)
                .build();
    }
}
