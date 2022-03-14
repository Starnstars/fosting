package com.lee.fosting.login.domain;

import javax.servlet.http.Cookie;
import java.time.LocalDate;
import java.util.Date;

public class MemberInfo {
    private String memberId;//입력
    private Long memberIndex;//부여
    private String memberRole;//부여

    private String memberName;//입력
    private String memberPass;//입력

    private String memberPhone;//입력
    private String memberEmail;//입력
    private LocalDate memberBirth;//입력

    private LocalDate dateCreated;//부여
    private LocalDate lastCheck;//부여
    private Cookie member;//부여

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Long getMemberIndex() {
        return memberIndex;
    }

    public void setMemberIndex(Long memberIndex) {
        this.memberIndex = memberIndex;
    }

    public String getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(String memberRole) {
        this.memberRole = memberRole;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberPass() {
        return memberPass;
    }

    public void setMemberPass(String memberPass) {
        this.memberPass = memberPass;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public LocalDate getMemberBirth() {
        return memberBirth;
    }

    public void setMemberBirth(LocalDate memberBirth) {
        this.memberBirth = memberBirth;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getLastCheck() {
        return lastCheck;
    }

    public void setLastCheck(LocalDate lastCheck) {
        this.lastCheck = lastCheck;
    }

    public Cookie getMember() {
        return member;
    }

    public void setMember(Cookie member) {
        this.member = member;
    }
}
