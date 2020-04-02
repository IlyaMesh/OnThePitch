package com.onthepitch.shared.model;


public class PostResult {
    private Long post_id;
    private String username;
    private String header;
    private String text;
    private String created_at;
    private Integer comments_count;

    public PostResult() {
    }


    public PostResult(
            Long post_id,
            String username,
            String header,
            String text,
            String created_at,
            Integer comments_count) {
        this.post_id = post_id;
        this.username = username;
        this.header = header;
        this.text = text;
        this.created_at = created_at;
        this.comments_count = comments_count;
    }

    public Integer getComments_count() {
        return comments_count;
    }

    public void setComments_count(Integer comments_count) {
        this.comments_count = comments_count;
    }

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
