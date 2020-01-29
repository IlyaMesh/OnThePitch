package com.onthepitch.backend.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.util.Date;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "POST_SEQ")
    @SequenceGenerator(sequenceName = "POSTS_SEC",allocationSize = 1,name = "POST_SEQ")
    private Long post_id;
    private Long user_id;
    private String header;
    private String text;
    private Date created_at;

    public Post(Long user_id, String header, String text, Date created_at) {
        this.user_id = user_id;
        this.header = header;
        this.text = text;
        this.created_at = created_at;
    }

    public Post() {
    }

    @Override
    public String toString() {
        return "Post{" +
                "post_id=" + post_id +
                ", user_id=" + user_id +
                ", header='" + header + '\'' +
                ", text='" + text + '\'' +
                ", created_at=" + created_at +
                '}';
    }

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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
