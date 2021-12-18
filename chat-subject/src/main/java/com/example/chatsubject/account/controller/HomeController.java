package com.example.chatsubject.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }
}
