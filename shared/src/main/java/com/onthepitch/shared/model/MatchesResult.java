package com.onthepitch.shared.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class MatchesResult {
    private Long match_id;
    private Long homeTeam_id;
    private String homeTeam_name;
    private Long awayTeam_id;
    private String awayTeam_name;
    private Integer homeTeamScored;
    private Integer awayTeamScored;
    private Integer homeTeamPenalties;
    private Integer awayTeamPenalties;
    private Date matchTime;
    private Long league_id;
    private String league_name;

    @JsonCreator
    public MatchesResult(
            @JsonProperty("match_id") Long match_id,
            @JsonProperty("homeTeam_id") Long homeTeam_id,
            @JsonProperty("homeTeam_name") String homeTeam_name,
            @JsonProperty("awayTeam_id") Long awayTeam_id,
            @JsonProperty("awayTeam_name") String awayTeam_name,
            @JsonProperty("homeTeamScored") Integer homeTeamScored,
            @JsonProperty("awayTeamScored") Integer awayTeamScored,
            @JsonProperty("homeTeamPenalties") Integer homeTeamPenalties,
            @JsonProperty("awayTeamPenalties") Integer awayTeamPenalties,
            @JsonProperty("matchTime") Date matchTime,
            @JsonProperty("league_id") Long league_id,
            @JsonProperty("league_name") String league_name) {
        this.match_id = match_id;
        this.homeTeam_id = homeTeam_id;
        this.homeTeam_name = homeTeam_name;
        this.awayTeam_id = awayTeam_id;
        this.awayTeam_name = awayTeam_name;
        this.homeTeamScored = homeTeamScored;
        this.awayTeamScored = awayTeamScored;
        this.homeTeamPenalties = homeTeamPenalties;
        this.awayTeamPenalties = awayTeamPenalties;
        this.matchTime = matchTime;
        this.league_id = league_id;
        this.league_name = league_name;
    }

    public Long getMatch_id() {
        return match_id;
    }

    public void setMatch_id(Long match_id) {
        this.match_id = match_id;
    }

    public Long getHomeTeam_id() {
        return homeTeam_id;
    }

    public void setHomeTeam_id(Long homeTeam_id) {
        this.homeTeam_id = homeTeam_id;
    }

    public String getHomeTeam_name() {
        return homeTeam_name;
    }

    public void setHomeTeam_name(String homeTeam_name) {
        this.homeTeam_name = homeTeam_name;
    }

    public Long getAwayTeam_id() {
        return awayTeam_id;
    }

    public void setAwayTeam_id(Long awayTeam_id) {
        this.awayTeam_id = awayTeam_id;
    }

    public String getAwayTeam_name() {
        return awayTeam_name;
    }

    public void setAwayTeam_name(String awayTeam_name) {
        this.awayTeam_name = awayTeam_name;
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

    public void setMatchTime(Date matchTime) {
        this.matchTime = matchTime;
    }

    public Long getLeague_id() {
        return league_id;
    }

    public void setLeague_id(Long league_id) {
        this.league_id = league_id;
    }

    public String getLeague_name() {
        return league_name;
    }

    public void setLeague_name(String league_name) {
        this.league_name = league_name;
    }
}
