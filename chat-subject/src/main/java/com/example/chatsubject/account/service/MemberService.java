package com.example.chatsubject.account.service;


import com.example.chatsubject.account.domain.Member;
import com.example.chatsubject.account.domain.MemberRepository;
import com.example.chatsubject.account.dto.MemberSignUpRequest;
import com.example.chatsubject.account.dto.MemberSignUpResponse;
import com.example.chatsubject.account.exception.DuplicatedUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberSignUpResponse signUpMember(MemberSignUpRequest memberSignUpRequest) {
        Member member = memberSignUpRequest.toEntity();
        validateDuplicatedUser(memberSignUpRequest);
        Member savedMember = memberRepository.save(member);
        return MemberSignUpResponse.builder()
                .name(savedMember.getNickname())
                .email(savedMember.getEmail())
                .build();
    }

    private void validateDuplicatedUser(MemberSignUpRequest memberSignUpRequest) throws DuplicatedUserException {
        if (memberRepository.existsByEmail(memberSignUpRequest.getEmail())) {
            throw new DuplicatedUserException("기존 회원이 존재합니다.");
        }
    }
}
