package com.onthepitch.backend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Class for match entity
 */
@Getter
@Setter
@NoArgsConstructor
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

}
