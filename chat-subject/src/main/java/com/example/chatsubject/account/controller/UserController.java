package com.example.chatsubject.account.controller;

import com.example.chatsubject.account.dto.UserSignUpRequest;
import com.example.chatsubject.account.dto.UserSignUpResponse;
import com.example.chatsubject.account.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/signup")
    public String getSignUpPage() {
        return "sign-up";
    }

    @PostMapping(value = "/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public String signUp(Model model, @ModelAttribute UserSignUpRequest userSignUpRequest) {
        log.debug("{}", userSignUpRequest);
        UserSignUpResponse userSignUpResponse = userService.signUpMember(userSignUpRequest);
        model.addAttribute("member", userSignUpResponse);
        log.debug("{}", userSignUpResponse);
        return "redirect:/";
    }
}
