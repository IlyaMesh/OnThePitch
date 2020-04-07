package com.onthepitch.backend.model;

import javax.persistence.*;
import java.util.List;

/**
 * Class for post's comment entity
 */
@Entity
@Table(name = "USER_COMMENT")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POST_SEQ")
    @SequenceGenerator(sequenceName = "POSTS_SEC", allocationSize = 1, name = "POST_SEQ")
    private Long comment_id;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User author;
    @ManyToOne(optional = true)
    @JoinColumn(name = "REPLY_ID")
    private Comment replyTo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "replyTo",orphanRemoval = true)
    private List<Comment> comments;
    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private Post post;
    private String text;

    public Comment() {
    }

    public Long getComment_id() {
        return comment_id;
    }

    public void setComment_id(Long comment_id) {
        this.comment_id = comment_id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Comment getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(Comment replyTo) {
        this.replyTo = replyTo;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
