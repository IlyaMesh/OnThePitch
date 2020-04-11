package com.onthepitch.backend.service.serviceImpl;

import com.onthepitch.backend.commands.StandingsTeam;
import com.onthepitch.backend.model.Match;
import com.onthepitch.backend.service.MatchService;
import com.onthepitch.backend.service.StandingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StandingsServiceImpl implements StandingsService {
    @Autowired
    private MatchService matchService;

    @Override
    public List<StandingsTeam> getStandings(Long leagueId) {
        List<Match> matches = matchService.getMatchesInLeagueAndCurrentSeason(leagueId);
        List<StandingsTeam> result = new ArrayList<>();
        //перебирать матчи, добавляем обе команды в список
        for (Match match : matches) {
            //если нет в множетсве клубов добавляем
            if(!result.contains(new StandingsTeam(match.getHomeTeam()))){//!!!!
                StandingsTeam homeTeam = new StandingsTeam(match.getHomeTeam());
                result.add(homeTeam);
            }
            if(!result.contains(new StandingsTeam(match.getAwayTeam()))){
                StandingsTeam awayTeam = new StandingsTeam(match.getAwayTeam());
                result.add(awayTeam);
            }


            //блок парсинга результатов матча
            StandingsTeam homeeTeam = result.stream()
                    .filter(standingsTeam -> match.getHomeTeam().getClub_name().equals(standingsTeam.getClub().getClub_name()))
                    .findAny()
                    .orElse(null);
            StandingsTeam awayyTeam = result.stream()
                    .filter(standingsTeam -> match.getAwayTeam().getClub_name().equals(standingsTeam.getClub().getClub_name()))
                    .findAny()
                    .orElse(null);
            if (getResult(match) > 0) {
                //победа домашней команды
                homeeTeam.setWins(homeeTeam.getWins() + 1);
                awayyTeam.setLoses(awayyTeam.getLoses() + 1);
            } else if (getResult(match) < 0) {
                //поражение домашней команды
                homeeTeam.setLoses(homeeTeam.getLoses() + 1);
                awayyTeam.setWins(awayyTeam.getWins() + 1);
            } else {
                //ничейка
                homeeTeam.setDraws(homeeTeam.getDraws() + 1);
                awayyTeam.setDraws(awayyTeam.getDraws() + 1);
            }
            homeeTeam.setGoalsScored(homeeTeam.getGoalsScored() + match.getHomeTeamScored());
            homeeTeam.setGoalsAgainst(homeeTeam.getGoalsAgainst() + match.getAwayTeamScored());
            awayyTeam.setGoalsScored(awayyTeam.getGoalsScored() + match.getAwayTeamScored());
            awayyTeam.setGoalsAgainst(awayyTeam.getGoalsAgainst() + match.getHomeTeamScored());
            homeeTeam.setMatchesPlayed(homeeTeam.getMatchesPlayed() + 1);
            awayyTeam.setMatchesPlayed(awayyTeam.getMatchesPlayed() + 1);

        }
        //подсчет очков
        for (StandingsTeam standingsTeam : result) {
            standingsTeam.setPoints(standingsTeam.getWins() * 3 + standingsTeam.getDraws());
        }

        return result.stream().sorted(new StandingTeamComparator().reversed()).collect(Collectors.toList());
    }

    private int getResult(Match match) {
        //if positive - win, 0 - draw, negative - lose
        return match.getHomeTeamScored() - match.getAwayTeamScored();
    }
}
class StandingTeamComparator implements Comparator<StandingsTeam>{

    @Override
    public int compare(StandingsTeam o1, StandingsTeam o2) {
        return o1.getPoints().compareTo(o2.getPoints());
    }
}
