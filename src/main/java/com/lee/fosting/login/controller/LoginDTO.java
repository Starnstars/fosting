package com.lee.fosting.login.controller;

public class LoginDTO {
    private String Id;
    private String pass;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
