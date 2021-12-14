package com.example.chatsubject.account.domain;

import lombok.*;

import javax.persistence.*;

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

    @Column(name = "member_name",nullable = false)
    private String name;

    @Column(name = "member_password", nullable = false)
    private String password;

    @Builder
    public Member(String name, String password) {
        this.name = name;
        this.password = password;
        validate();
    }

    private void validate() {
        validateName();
        validatePassword();
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
}
