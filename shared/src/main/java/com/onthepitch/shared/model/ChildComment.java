package com.onthepitch.shared.model;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

public class ChildComment {
    private Long comment_id;
    private String username;
    private String user_logo;
    private String text;
    private Integer likes;
    private Integer dislikes;
    private String reply_id;
    private String reply_name;
    private Boolean isLiked;
    private Boolean isDisliked;

    public ChildComment() {
    }

    public ChildComment(Long comment_id, String username, String user_logo, String text, Integer likes, Integer dislikes, String reply_id, String reply_name, Boolean isLiked, Boolean isDisliked) {
        this.comment_id = comment_id;
        this.username = username;
        this.user_logo = user_logo;
        this.text = text;
        this.likes = likes;
        this.dislikes = dislikes;
        this.reply_id = reply_id;
        this.reply_name = reply_name;
        this.isLiked = isLiked;
        this.isDisliked = isDisliked;
    }


    public Boolean getLiked() {
        return isLiked;
    }

    public void setLiked(Boolean liked) {
        isLiked = liked;
    }

    public Boolean getDisliked() {
        return isDisliked;
    }

    public void setDisliked(Boolean disliked) {
        isDisliked = disliked;
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

    public String getReply_id() {
        return reply_id;
    }

    public void setReply_id(String reply_id) {
        this.reply_id = reply_id;
    }

    public String getUser_logo() {
        return user_logo;
    }

    public void setUser_logo(String user_logo) {
        this.user_logo = user_logo;
    }
}
