package com.example.chatsubject.account.service;


import com.example.chatsubject.account.domain.Member;
import com.example.chatsubject.account.domain.MemberRepository;
import com.example.chatsubject.account.dto.MemberSignUpRequest;
import com.example.chatsubject.account.dto.MemberSignUpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberSignUpResponse signUpMember(MemberSignUpRequest memberSignUpResponse) {
        Member member = memberSignUpResponse.toEntity();
        Member savedMember = memberRepository.save(member);
        return MemberSignUpResponse.builder()
                .name(savedMember.getName())
                .email(savedMember.getEmail())
                .build();
    }
}
