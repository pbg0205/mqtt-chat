package com.example.chatsubject.account.controller;

import com.example.chatsubject.account.dto.UserSignUpRequest;
import com.example.chatsubject.account.service.UserSignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserSignUpController {

    private final UserSignUpService userSignUpService;

    @GetMapping(value = "/signup")
    public String signUpForm(Model model) {
        model.addAttribute("userSignUpRequest", new UserSignUpRequest());
        return "signup";
    }

    @PostMapping(value = "/signup")
    public String signUp(@ModelAttribute @Valid UserSignUpRequest userSignUpRequest, BindingResult result) {
        if (result.hasErrors()) {
            return "signup";
        }

        userSignUpService.signUpMember(userSignUpRequest);
        return "redirect:/login";
    }

}
