package com.lee.fosting.Restaurant.domain;

import java.sql.Timestamp;

public class RestaurantInfo {
    private long resIndex;//부여
    private String resCategory;//입력
    private String resPostName;//입력
    private String resName;//입력
    private String resLocate;//입력
    private String resComment;//입력
    private String resMember;//부여
    private double resRecommend;//평가
    private Timestamp resPostDate;//부여
    private String resImgLocate;//부여
    private String resLatLng;//입력
    private String resRecommendCookie;//부여

    public long getResindex() {
        return resIndex;
    }

    public void setResindex(long resIndex) {
        this.resIndex = resIndex;
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

    public String getResMember() {
        return resMember;
    }

    public void setResMember(String resMember) {
        this.resMember = resMember;
    }

    public String getResComment() {
        return resComment;
    }

    public void setResComment(String resComment) {
        this.resComment = resComment;
    }

    public double getResRecommend() {
        return resRecommend;
    }

    public void setResRecommend(double resRecomend) {
        this.resRecommend = resRecomend;
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

    public String getResLatLng() {
        return resLatLng;
    }

    public void setResLatLng(String resLatLng) {
        this.resLatLng = resLatLng;
    }

    public String getResRecommendCookie() {
        return resRecommendCookie;
    }

    public void setResRecommendCookie(String resRecommendCookie) {
        this.resRecommendCookie = resRecommendCookie;
    }
}