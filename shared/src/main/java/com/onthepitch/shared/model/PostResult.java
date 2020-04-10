package com.onthepitch.shared.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class PostResult {
    private Long post_id;
    private String username;
    private String header;
    private String text;
    private String created_at;
    private Integer comments_count;
    private Integer likes;
    private Integer dislikes;
    private Boolean isLiked;
    private Boolean isDisliked;

//    public PostResult() {
//    }
//
//
    public PostResult(
            Long post_id,
            String username,
            String header,
            String text,
            String created_at,
            Integer comments_count,
            Integer likes,
            Integer dislikes,
            Boolean isLiked,
            Boolean isDisliked) {
        this.post_id = post_id;
        this.username = username;
        this.header = header;
        this.text = text;
        this.created_at = created_at;
        this.comments_count = comments_count;
        this.likes = likes;
        this.dislikes = dislikes;
        this.isLiked = isLiked;
        this.isDisliked = isDisliked;
    }
//
//    public Integer getComments_count() {
//        return comments_count;
//    }
//
//    public void setComments_count(Integer comments_count) {
//        this.comments_count = comments_count;
//    }
//
//    public Long getPost_id() {
//        return post_id;
//    }
//
//    public void setPost_id(Long post_id) {
//        this.post_id = post_id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getHeader() {
//        return header;
//    }
//
//    public void setHeader(String header) {
//        this.header = header;
//    }
//
//    public String getText() {
//        return text;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }
//
//    public String getCreated_at() {
//        return created_at;
//    }
//
//    public void setCreated_at(String created_at) {
//        this.created_at = created_at;
//    }
//
//    public Integer getLikes() {
//        return likes;
//    }
//
//    public void setLikes(Integer likes) {
//        this.likes = likes;
//    }
//
//    public Integer getDislikes() {
//        return dislikes;
//    }
//
//    public void setDislikes(Integer dislikes) {
//        this.dislikes = dislikes;
//    }
//
//    public Boolean getLiked() {
//        return isLiked;
//    }
//
//    public void setLiked(Boolean liked) {
//        isLiked = liked;
//    }
//
//    public Boolean getDisliked() {
//        return isDisliked;
//    }
//
//    public void setDisliked(Boolean disliked) {
//        isDisliked = disliked;
//    }
}
