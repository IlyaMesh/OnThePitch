package com.onthepitch.backend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Class for post's comment entity
 */
@Getter
@Setter
@NoArgsConstructor
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

    @ManyToOne()
    @JoinColumn(name = "REPLY_ID")
    private Comment replyTo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "replyTo",orphanRemoval = true)
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private Post post;

    private String text;

}
