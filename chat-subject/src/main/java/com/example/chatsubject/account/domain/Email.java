package com.example.chatsubject.account.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Setter
@Getter
@Embeddable
@EqualsAndHashCode(of = {"email"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email {

    @Column(nullable = false, unique = true)
    private String email;

    public Email(String email) {
        this.email = email;
    }
}
