package com.lee.fosting.login.controller;

import java.sql.Timestamp;
import java.time.LocalDate;

public class SignUpDTO {

    private String id;//입력
    private String name;//입력
    private String pass;//입력
    private String phone;//입력
    private String email;//입력
    private int index;
    private String role;
    private Timestamp dateCreated;

    public SignUpDTO() {
    }

    public SignUpDTO(String id, String name, String pass, String phone, String email) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.phone = phone;
        this.email = email;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

}
