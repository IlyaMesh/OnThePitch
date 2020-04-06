package com.onthepitch.backend.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RATING_SEQ")
    @SequenceGenerator(sequenceName = "RATINGS_SEC", allocationSize = 1, name = "RATING_SEQ")
    private Long rating_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private User user;
    private Long note_id;
    private Date rated_at;
    private boolean isLike;

    public Rating() {
    }

    public Long getRating_id() {
        return rating_id;
    }

    public void setRating_id(Long rating_id) {
        this.rating_id = rating_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getNote_id() {
        return note_id;
    }

    public void setNote_id(Long note_id) {
        this.note_id = note_id;
    }

    public Date getRated_at() {
        return rated_at;
    }

    public void setRated_at(Date rated_at) {
        this.rated_at = rated_at;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }
}
