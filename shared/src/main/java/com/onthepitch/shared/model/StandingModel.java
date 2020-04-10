package com.onthepitch.shared.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StandingModel {
    private int club_id;
    private String club_name;
    private String logo;
    private int matchesPlayed;
    private int wins;
    private int draws;
    private int loses;
    private int points;
    private int goalsScored;
    private int goalsAgainst;

}
