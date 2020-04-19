package com.onthepitch.backend.model;

import lombok.Data;

@Data
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
}


