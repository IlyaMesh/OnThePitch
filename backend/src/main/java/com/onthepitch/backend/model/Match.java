package com.onthepitch.backend.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Class for match entity
 */
@Entity
@Table(name = "MATCHES")
public class Match {
    @Id
    private Long match_id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "HOME_TEAM_ID", referencedColumnName = "CLUB_ID")
    private Club homeTeam;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AWAY_TEAM_ID", referencedColumnName = "CLUB_ID")
    private Club awayTeam;
    @Column(name = "HOME_TEAM_SCORED")
    private Integer homeTeamScored;
    @Column(name = "AWAY_TEAM_SCORED")
    private Integer awayTeamScored;
    @Column(name = "HOME_TEAM_PENALTIES")
    private Integer homeTeamPenalties;
    @Column(name = "AWAY_TEAM_PENALTIES")
    private Integer awayTeamPenalties;
    private Date matchTime;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LEAGUE_ID", referencedColumnName = "LEAGUE_ID")
    private League league;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SEASON_ID", referencedColumnName = "SEASON_ID")
    private Season season;
    @Column(name = "LAST_UPDATED")
    private Date lastUpdated;


    public Match() {
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Long getMatch_id() {
        return match_id;
    }

    public void setMatch_id(Long match_id) {
        this.match_id = match_id;
    }

    public Club getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Club homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Club getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Club awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Integer getHomeTeamScored() {
        return homeTeamScored;
    }

    public void setHomeTeamScored(Integer homeTeamScored) {
        this.homeTeamScored = homeTeamScored;
    }

    public Integer getAwayTeamScored() {
        return awayTeamScored;
    }

    public void setAwayTeamScored(Integer awayTeamScored) {
        this.awayTeamScored = awayTeamScored;
    }

    public Integer getHomeTeamPenalties() {
        return homeTeamPenalties;
    }

    public void setHomeTeamPenalties(Integer homeTeamPenalties) {
        this.homeTeamPenalties = homeTeamPenalties;
    }

    public Integer getAwayTeamPenalties() {
        return awayTeamPenalties;
    }

    public void setAwayTeamPenalties(Integer awayTeamPenalties) {
        this.awayTeamPenalties = awayTeamPenalties;
    }

    public Date getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(Date match_time) {
        this.matchTime = match_time;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }
}
