package com.onthepitch.backend.model;

import java.util.Objects;

public class StandingsTeam {
    private Club club;
    private Integer matchesPlayed;
    private Integer wins;
    private Integer draws;
    private Integer loses;
    private Integer points;
    private Integer goalsScored;
    private Integer goalsAgainst;

    public StandingsTeam(Club club) {
        this.club = club;
        this.matchesPlayed = 0;
        this.wins = 0;
        this.draws = 0;
        this.loses = 0;
        this.points = 0;
        this.goalsScored = 0;
        this.goalsAgainst = 0;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Integer getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(Integer matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getDraws() {
        return draws;
    }

    public void setDraws(Integer draws) {
        this.draws = draws;
    }

    public Integer getLoses() {
        return loses;
    }

    public void setLoses(Integer loses) {
        this.loses = loses;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(Integer goalsScored) {
        this.goalsScored = goalsScored;
    }

    public Integer getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(Integer goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StandingsTeam that = (StandingsTeam) o;
        return Objects.equals(club, that.club);
    }

    @Override
    public int hashCode() {
        return Objects.hash(club, matchesPlayed, wins, draws, loses, points, goalsScored, goalsAgainst);
    }
}


