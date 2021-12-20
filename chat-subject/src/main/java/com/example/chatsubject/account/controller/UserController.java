package com.example.chatsubject.account.controller;

import com.example.chatsubject.account.dto.UserSignUpRequest;
import com.example.chatsubject.account.dto.UserSignUpResponse;
import com.example.chatsubject.account.exception.DuplicatedUserException;
import com.example.chatsubject.account.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/signup")
    public String signUpForm() {
        return "signup";
    }

    @GetMapping(value = "/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping(value = "/signup")
    public RedirectView signUp(@ModelAttribute UserSignUpRequest userSignUpRequest) {
        UserSignUpResponse userSignUpResponse = userService.signUpMember(userSignUpRequest);
        log.debug("{}", userSignUpResponse);
        return new RedirectView("/login");
    }

    @ExceptionHandler(DuplicatedUserException.class)
    public void handleDuplicatedUserException(Model model, Exception exception) {
        model.addAttribute("error", exception);
    }
}
