package com.lee.fosting.login.repository;

import com.lee.fosting.login.domain.MemberInfo;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.lang.reflect.Member;

import static org.junit.jupiter.api.Assertions.*;

class JdbcTemplateMemberRepositoryTest {



    @Test
    void create() {
        //given
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMemberName("lee");
        //when

        //then

    }

    @Test
    void findById() {
    }

    @Test
    void findByName() {
    }

    @Test
    void delete() {
    }
}