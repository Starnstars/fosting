package com.lee.fosting.login.controller;

import com.lee.fosting.login.domain.MemberInfo;
import com.lee.fosting.login.service.LoginRestService;
import com.lee.fosting.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    @Autowired
    LoginRestService loginRestService;

    @GetMapping("/login")
    public String getLogin() {
        return "login/login";
    }

    @PostMapping("/login")
    public String postLogin(String id, String pass, HttpServletResponse response) {
        Boolean login = loginRestService.login(id, pass);
        //쿠키 존재 확인, 쿠키 여부에 따라 브라우저 기능 분기
        if (login) {
            int idx = loginRestService.findIndexById(id);
            Cookie cookie = new Cookie(id, "fosting_idx" + String.valueOf(idx));
            cookie.setMaxAge(60 * 30);
            //쿠키 생성
            response.addCookie(cookie);
            return "redirect:/";
        } else {
            return "error";
        }
    }

    @GetMapping("/signUp")
    public String getSignUp() {
        return "login/signUp";
    }

    @PostMapping("/signUp")
    public String postSignUp() {
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        deleteCookie(request, response);
        return "redirect:/";
    }

    private void deleteCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getValue().contains("fosting_idx")) {
                c.setMaxAge(0);
                response.addCookie(c);
            }
        }
    }
}
