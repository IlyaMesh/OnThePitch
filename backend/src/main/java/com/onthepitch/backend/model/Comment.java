package com.onthepitch.backend.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USER_COMMENT")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "POST_SEQ")
    private Long comment_id;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User author;
    @ManyToOne(optional = true)
    @JoinColumn(name = "REPLY_ID")
    private Comment replyTo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "replyTo")
    private List<Comment> comments;
    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private Post post;
    private String text;
    private Integer likes;
    private Integer dislikes;
    private Integer reports;

    public Comment() {
        likes =0;
        dislikes=0;
        reports=0;
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
}
