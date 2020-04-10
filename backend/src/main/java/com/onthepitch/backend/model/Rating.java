package com.onthepitch.backend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
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

}
