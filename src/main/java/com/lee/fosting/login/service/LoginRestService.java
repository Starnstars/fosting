package com.lee.fosting.login.service;

import com.lee.fosting.login.controller.LoginDTO;
import com.lee.fosting.login.controller.SignUpDTO;
import com.lee.fosting.login.repository.MemberMapper;
import org.apache.ibatis.util.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.MapUtils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginRestService {

    @Autowired
    MemberMapper memberMapper;

    int sequence;

    public boolean login(String id, String pass) {

        HashMap<String, Object> findById = memberMapper.findById(id);
        if (MapUtils.isEmpty(findById)) {
            return false;
        } else {
            boolean memberId = findById.get("memberId").equals(id);
            boolean memberPass = findById.get("memberPass").equals(pass);
            if (memberId && memberPass) {
                return true;
            } else {
                return false;
            }
        }
    }

    public void signUp(SignUpDTO signUpDTO) {
        sequence = memberMapper.memberMaxIndex();
        signUpDTO.setIndex(++sequence);
        signUpDTO.setRole("사용자");
        signUpDTO.setDateCreated(new Timestamp(System.currentTimeMillis()));
        memberMapper.signUp(signUpDTO);
    }

    public HashMap<String, Object> findById(String memberId){
        return memberMapper.findById(memberId);
    }

    public int findIndexById(String memberId){
        return memberMapper.findIndexById(memberId);
    }
}
