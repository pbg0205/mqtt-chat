package com.example.chatsubject.account.controller;

import com.example.chatsubject.account.dto.MemberSignUpRequest;
import com.example.chatsubject.account.dto.MemberSignUpResponse;
import com.example.chatsubject.account.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public String signUp(Model model, @ModelAttribute MemberSignUpRequest memberSignUpRequest) {
        MemberSignUpResponse memberSignUpResponse = memberService.signUpMember(memberSignUpRequest);
        model.addAttribute("member", memberSignUpResponse);
        return "/index";
    }
}
