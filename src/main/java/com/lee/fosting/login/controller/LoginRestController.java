package com.lee.fosting.login.controller;

import com.lee.fosting.login.service.LoginRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.MapUtils;

import java.util.HashMap;

@RestController
public class LoginRestController {

    @Autowired
    LoginRestService loginRestService;

    @PostMapping("/Login")
    public Boolean login(String id, String pass) {
        return loginRestService.login(id, pass);
    }

    @PostMapping("/SignUp")
    public Boolean SignUp(String id, String name, String pass, String phone, String email) {
        HashMap<String, Object> byId = loginRestService.findById(id);
        boolean empty = MapUtils.isEmpty(byId);

        if (empty) {
            SignUpDTO signUpDTO = new SignUpDTO(id, name, pass, phone, email);
            loginRestService.signUp(signUpDTO);
            return true;
        }else{
            return false;
        }


    }

}
