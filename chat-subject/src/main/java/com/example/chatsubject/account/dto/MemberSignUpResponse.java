package com.example.chatsubject.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberSignUpResponse {

    private String name;
    private String email;

}
