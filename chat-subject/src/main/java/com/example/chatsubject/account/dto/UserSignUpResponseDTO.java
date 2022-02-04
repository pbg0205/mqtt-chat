package com.example.chatsubject.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class UserSignUpResponseDTO {

    private String name;
    private String email;

}
