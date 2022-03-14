package com.lee.fosting.Restaurant.domain;

import java.sql.Timestamp;

public class RestaurantComment {
    private long resindex;
    private String commentMember;
    private Timestamp commentTime;
    private String comment;
    private long commentindex;

    public long getResindex() {
        return resindex;
    }

    public void setResindex(long resindex) {
        this.resindex = resindex;
    }

    public String getCommentMember() {
        return commentMember;
    }

    public void setCommentMember(String commentMember) {
        this.commentMember = commentMember;
    }

    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getCommentindex() {
        return commentindex;
    }

    public void setCommentindex(long commentindex) {
        this.commentindex = commentindex;
    }
}
