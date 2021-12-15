package com.example.chatsubject.account.service;

import com.example.chatsubject.account.domain.Member;
import com.example.chatsubject.account.domain.MemberRepository;
import com.example.chatsubject.account.dto.MemberSignUpRequest;
import com.example.chatsubject.account.dto.MemberSignUpResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = MemberService.class)
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @MockBean
    private MemberRepository memberRepository;

    @Test
    @DisplayName("적절한 입력 값일 경우, 정상 등록한다.")
    void create_member_when_input_is_correct() {
        //given
        MemberSignUpRequest request
                = new MemberSignUpRequest("sample", "123", "sample@rsupport.com");
        Member member = request.toEntity();
        when(memberRepository.save(member)).thenReturn(member);

        //when
        MemberSignUpResponse response = memberService.signUpMember(request);

        //then
        assertThat(request.getName()).isEqualTo(response.getName());
        assertThat(request.getEmail()).isEqualTo(response.getEmail());
    }
}
