package com.example.chatsubject.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserErrorController {

    @GetMapping("/expired_session")
    public String getExpiredSessionForm() {
        return "expired_session_page";
    }

}
