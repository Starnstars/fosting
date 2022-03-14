package com.lee.fosting.login.service;

import com.lee.fosting.login.domain.MemberInfo;
import com.lee.fosting.login.repository.JdbcTemplateMemberRepository;
import com.lee.fosting.login.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class LoginService {
    private final MemberRepository memberRepository;

//    @Autowired
    public LoginService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void SignUp(MemberInfo memberInfo) {
        DuplicateMemberCheck(memberInfo);
        memberRepository.SignUp(memberInfo);
    }

    public Boolean login(MemberInfo memberInfo) {
        MemberInfo dbCheck = memberRepository.FindById(memberInfo.getMemberId()).get();
        return memberInfo.getMemberPass().equals(dbCheck.getMemberPass());
    }

    public long findById(String memberId){
        Long memberIndex = memberRepository.FindById(memberId).get().getMemberIndex();
        return memberIndex;
    }

    private void DuplicateMemberCheck(MemberInfo memberInfo) {
        memberRepository.FindById(memberInfo.getMemberId()).ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }
}
