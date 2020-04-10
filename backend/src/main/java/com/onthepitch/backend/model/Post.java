package com.onthepitch.backend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Class for post entity
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POST_SEQ")
    @SequenceGenerator(sequenceName = "POSTS_SEC", allocationSize = 1, name = "POST_SEQ")
    private Long post_id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private User author;
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments;

    private String header;
    private String text;
    private Date created_at;

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

}
