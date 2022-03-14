package com.lee.fosting.Restaurant.controller;

import java.sql.Timestamp;
import java.time.LocalDate;

public class PostFormDTO {
    private int resIndex;//부여
    private String resCategory;//입력
    private String resPostName;//입력
    private String resName;//입력
    private String resComment;//입력
    private String resLocate;//입력
    private String resMember;//부여
    private double resRecommend;//부여
    private Timestamp resPostDate;//부여
    private String resImgLocate;//부여
    private String resLatLng;//입력
    private String resRecommendCookie;// . 부여 null방지

    public PostFormDTO() {
    }

    public PostFormDTO(String resCategory, String resPostName, String resName, String resComment, String resLocate, String resLatLng) {
        this.resCategory = resCategory;
        this.resPostName = resPostName;
        this.resName = resName;
        this.resComment = resComment;
        this.resLocate = resLocate;
        this.resLatLng = resLatLng;
    }

    public int getResIndex() {
        return resIndex;
    }

    public void setResIndex(int resIndex) {
        this.resIndex = resIndex;
    }

    public String getResMember() {
        return resMember;
    }

    public void setResMember(String resMember) {
        this.resMember = resMember;
    }

    public double getResRecommend() {
        return resRecommend;
    }

    public void setResRecommend(double resRecommend) {
        this.resRecommend = resRecommend;
    }

    public Timestamp getResPostDate() {
        return resPostDate;
    }

    public void setResPostDate(Timestamp resPostDate) {
        this.resPostDate = resPostDate;
    }

    public String getResImgLocate() {
        return resImgLocate;
    }

    public void setResImgLocate(String resImgLocate) {
        this.resImgLocate = resImgLocate;
    }

    public String getResRecommendCookie() {
        return resRecommendCookie;
    }

    public void setResRecommendCookie(String resRecommendCookie) {
        this.resRecommendCookie = resRecommendCookie;
    }

    public String getResCategory() {
        return resCategory;
    }

    public void setResCategory(String resCategory) {
        this.resCategory = resCategory;
    }

    public String getResPostName() {
        return resPostName;
    }

    public void setResPostName(String resPostName) {
        this.resPostName = resPostName;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getResLocate() {
        return resLocate;
    }

    public void setResLocate(String resLocate) {
        this.resLocate = resLocate;
    }

    public String getResComment() {
        return resComment;
    }

    public void setResComment(String resComment) {
        this.resComment = resComment;
    }

    public String getResLatLng() {
        return resLatLng;
    }

    public void setResLatLng(String resLatLng) {
        this.resLatLng = resLatLng;
    }

}
