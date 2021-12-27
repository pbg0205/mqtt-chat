package com.example.chatsubject.account.controller;

import com.example.chatsubject.account.dto.UserSignUpRequest;
import com.example.chatsubject.account.dto.UserSignUpResponse;
import com.example.chatsubject.account.exception.DuplicatedUserException;
import com.example.chatsubject.account.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/signup")
    public String signUpForm(Model model) {
        model.addAttribute("userSignUpRequest", new UserSignUpRequest());
        return "signup";
    }

    @GetMapping(value = "/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping(value = "/signup")
    public String signUp(@Valid @ModelAttribute UserSignUpRequest userSignUpRequest,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "signup";
        }
        UserSignUpResponse userSignUpResponse = userService.signUpMember(userSignUpRequest);
        log.debug("{}", userSignUpResponse);
        return "redirect:/login";
    }

    @ExceptionHandler(DuplicatedUserException.class)
    public void handleDuplicatedUserException(Model model, Exception exception) {
        model.addAttribute("error", exception);
    }
}
