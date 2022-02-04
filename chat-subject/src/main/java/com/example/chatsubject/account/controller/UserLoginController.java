package com.example.chatsubject.account.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserLoginController {

    @GetMapping(value = "/login")
    public String loginForm() {
        return "login";
    }

}
