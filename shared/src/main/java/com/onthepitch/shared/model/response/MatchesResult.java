package com.onthepitch.shared.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MatchesResult {
    private Long match_id;
    private Long homeTeam_id;
    private String homeTeam_name;
    private String homeTeamLogo;
    private Long awayTeam_id;
    private String awayTeam_name;
    private String awayTeamLogo;
    private Integer homeTeamScored;
    private Integer awayTeamScored;
    private Integer homeTeamPenalties;
    private Integer awayTeamPenalties;
    private String matchTime;
    private Long league_id;
    private String league_name;

}
