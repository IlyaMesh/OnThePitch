package com.onthepitch.backend.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Class for post entity
 */
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POST_SEQ")
    @SequenceGenerator(sequenceName = "POSTS_SEC", allocationSize = 1, name = "POST_SEQ")
    private Long post_id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private User author;
    //    private Long user_id;
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments;

    private String header;
    private String text;
    private Date created_at;

    public Post(User author, String header, String text, Date created_at) {
        this.author = author;
        this.header = header;
        this.text = text;
        this.created_at = created_at;
    }

    public Post() {
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

    @Override
    public String toString() {
        return "Post{" +
                "post_id=" + post_id +
                ", author=" + author.toString() +
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
