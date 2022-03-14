package com.lee.fosting.login.repository;

import com.lee.fosting.login.domain.MemberInfo;

import java.util.Optional;

public interface MemberRepository {
    //생성 아이디,이름 조회 업데이트 삭제
    MemberInfo SignUp(MemberInfo memberInfo);
    Optional<MemberInfo> FindById(String id);
    Optional<MemberInfo> FindByName(String name);
//    MemberInfo Update(MemberInfo memberInfo);
    void Delete(MemberInfo memberInfo);
}
