package com.example.chatsubject.account.controller;

import com.example.chatsubject.account.dto.MemberSignUpRequest;
import com.example.chatsubject.account.dto.MemberSignUpResponse;
import com.example.chatsubject.account.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "/signup")
    public String getSignUpPage() {
        return "sign-up";
    }

    @PostMapping(value = "/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public String signUp(Model model, @ModelAttribute MemberSignUpRequest memberSignUpRequest) {
        log.info("{}", memberSignUpRequest);
        MemberSignUpResponse memberSignUpResponse = memberService.signUpMember(memberSignUpRequest);
        model.addAttribute("member", memberSignUpResponse);
        log.info("{}", memberSignUpResponse);
        return "redirect:/";
    }
}
