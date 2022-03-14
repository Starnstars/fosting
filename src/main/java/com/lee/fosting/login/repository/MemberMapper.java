package com.lee.fosting.login.repository;

import com.lee.fosting.login.controller.SignUpDTO;
import com.lee.fosting.login.domain.MemberInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Mapper
@Repository
public interface MemberMapper {

    void signUp(SignUpDTO signUpDTO);

    int memberMaxIndex();

    HashMap<String, Object> findById(String memberId);

    int findIndexById(String memberId);
//    ArrayList<HashMap<String, Object>> findAll();
//
//    HashMap<String, Object> findSelect(int person_id);
//
//    HashMap<String, Object> findAndTest(Map<String, Object> fatMap);
}
