package com.example.chatsubject.account.domain;

import lombok.*;

import javax.persistence.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Getter
@Table(name = "member")
@EqualsAndHashCode(of = {"id", "password"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id",nullable = false)
    private Long id;

    @Column(name = "member_name",nullable = false, unique = true)
    private String name;

    @Column(name = "member_email", nullable = false)
    private String email;

    @Column(name = "member_password", nullable = false)
    private String password;

    @Builder
    public Member(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
        validate();
    }

    private void validate() {
        validateName();
        validatePassword();
        validateEmail();
    }

    private void validateName() {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("이름이 존재하지 않습니다.");
        }
    }

    private void validatePassword() {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("비밀번호가 존재하지 않습니다.");
        }
    }

    private void validateEmail() {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("이메일이 올바르지 않습니다.");
        }
    }
}
