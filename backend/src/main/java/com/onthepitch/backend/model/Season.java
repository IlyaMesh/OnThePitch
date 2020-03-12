package com.onthepitch.backend.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Class for season entity
 */
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

    public Season() {
    }

    public Long getSeason_id() {
        return season_id;
    }

    public void setSeason_id(Long season_id) {
        this.season_id = season_id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Integer getCurrentMatchDay() {
        return currentMatchDay;
    }

    public void setCurrentMatchDay(Integer currentMatchDay) {
        this.currentMatchDay = currentMatchDay;
    }
}
