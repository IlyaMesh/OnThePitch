package com.onthepitch.shared.model;

import java.util.List;

public class ChildComment {
    private Long comment_id;
    private String username;
    private String text;
    private Integer likes;
    private Integer dislikes;
    private Integer reports;
    private String reply_id;
    private String reply_name;

    public ChildComment() {
    }

    public ChildComment(Long comment_id, String username, String text, Integer likes, Integer dislikes, Integer reports, String reply_id, String reply_name) {
        this.comment_id = comment_id;
        this.username = username;
        this.text = text;
        this.likes = likes;
        this.dislikes = dislikes;
        this.reports = reports;
        this.reply_id = reply_id;
        this.reply_name = reply_name;
    }

    public String getReply_name() {
        return reply_name;
    }

    public void setReply_name(String reply_name) {
        this.reply_name = reply_name;
    }

    public Long getComment_id() {
        return comment_id;
    }

    public void setComment_id(Long comment_id) {
        this.comment_id = comment_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }

    public Integer getReports() {
        return reports;
    }

    public void setReports(Integer reports) {
        this.reports = reports;
    }

    public String getReply_id() {
        return reply_id;
    }

    public void setReply_id(String reply_id) {
        this.reply_id = reply_id;
    }
}
