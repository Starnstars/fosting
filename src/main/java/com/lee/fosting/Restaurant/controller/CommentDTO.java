package com.lee.fosting.Restaurant.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class CommentDTO {
    private int commentIndex;
    private int resIndex;
    private String commentMember;
    private String commentTime;
    private String comment;

    public CommentDTO(int commentIndex, int resIndex, String commentMember,String commentTime, String comment) {
        this.commentIndex = commentIndex;
        this.resIndex = resIndex;
        this.commentMember = commentMember;
        this.commentTime = commentTime;
        this.comment = comment;
    }

    public int getCommentIndex() {
        return commentIndex;
    }

    public void setCommentIndex(int commentIndex) {
        this.commentIndex = commentIndex;
    }

    public int getResIndex() {
        return resIndex;
    }

    public void setResIndex(int resIndex) {
        this.resIndex = resIndex;
    }

    public String getCommentMember() {
        return commentMember;
    }

    public void setCommentMember(String commentMember) {
        this.commentMember = commentMember;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
