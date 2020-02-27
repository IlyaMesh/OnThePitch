package com.onthepitch.shared.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

//@Component
////@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class PostResult {
    private Long post_id;
    private String username;
    private String header;
    private String text;
    private Date created_at;

    @JsonCreator
    public PostResult(
            @JsonProperty("post_id") Long post_id,
            @JsonProperty("username") String username,
            @JsonProperty("header") String header,
            @JsonProperty("text") String text,
            @JsonProperty("created_at") Date created_at) {
        this.post_id = post_id;
        this.username = username;
        this.header = header;
        this.text = text;
        this.created_at = created_at;
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

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
