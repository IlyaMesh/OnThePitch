package com.onthepitch.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
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


