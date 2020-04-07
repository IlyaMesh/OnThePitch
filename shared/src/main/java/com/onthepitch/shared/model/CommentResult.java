package com.onthepitch.shared.model;

import java.util.List;

public class CommentResult {
    private Long comment_id;
    private String username;
    private String user_logo;
    private List<ChildComment> comments;
    private String text;
    private Integer likes;
    private Integer dislikes;
    private Boolean isLiked;
    private Boolean isDisliked;

    public CommentResult(Long comment_id, String username,String user_logo, List<ChildComment> comments, String text, Integer likes, Integer dislikes, Boolean isLiked, Boolean isDisliked) {
        this.comment_id = comment_id;
        this.username = username;
        this.user_logo= user_logo;
        this.comments = comments;
        this.text = text;
        this.likes = likes;
        this.dislikes = dislikes;
        this.isLiked = isLiked;
        this.isDisliked = isDisliked;
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


    public String getUser_logo() {
        return user_logo;
    }

    public void setUser_logo(String user_logo) {
        this.user_logo = user_logo;
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

    public List<ChildComment> getComments() {
        return comments;
    }

    public void setComments(List<ChildComment> comments) {
        this.comments = comments;
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

}
