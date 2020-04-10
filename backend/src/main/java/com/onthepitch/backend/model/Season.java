package com.onthepitch.backend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Class for season entity
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "SEASONS")
public class Season {
    @Id
    private Long season_id;
    private Date start_date;
    private Date end_date;
    @Column(name = "CURRENT_MATCH_DAY")
    private Integer currentMatchDay;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LEAGUE_ID", referencedColumnName = "LEAGUE_ID")
    private League league;

}
