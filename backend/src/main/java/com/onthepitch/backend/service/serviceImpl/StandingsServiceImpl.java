package com.onthepitch.backend.service.serviceImpl;

import com.onthepitch.backend.model.StandingsTeam;
import com.onthepitch.backend.model.Match;
import com.onthepitch.backend.service.MatchService;
import com.onthepitch.backend.service.StandingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StandingsServiceImpl implements StandingsService {
    @Autowired
    private MatchService matchService;

    @Override
    public List<StandingsTeam> getStandings(Long leagueId) {
        List<Match> matches = matchService.getMatchesInLeagueAndCurrentSeason(leagueId);
        Set<StandingsTeam> result = new HashSet<>();
        //перебирать матчи, добавляем обе команды в список
        for (Match match : matches) {

            result.add(new StandingsTeam(match.getHomeTeam()));
            result.add(new StandingsTeam(match.getAwayTeam()));

            //блок парсинга результатов матча
            StandingsTeam homeTeam = result.stream()
                    .filter(standingsTeam -> match.getHomeTeam().getClub_name().equals(standingsTeam.getClub().getClub_name()))
                    .findAny()
                    .orElse(null);
            StandingsTeam awayTeam = result.stream()
                    .filter(standingsTeam -> match.getAwayTeam().getClub_name().equals(standingsTeam.getClub().getClub_name()))
                    .findAny()
                    .orElse(null);
            if (getResult(match) > 0) {
                //победа домашней команды
                homeTeam.setWins(homeTeam.getWins() + 1);
                awayTeam.setLoses(awayTeam.getLoses() + 1);
            } else if (getResult(match) < 0) {
                //поражение домашней команды
                homeTeam.setLoses(homeTeam.getLoses() + 1);
                awayTeam.setWins(awayTeam.getWins() + 1);
            } else {
                //ничейка
                homeTeam.setDraws(homeTeam.getDraws() + 1);
                awayTeam.setDraws(awayTeam.getDraws() + 1);
            }
            homeTeam.setGoalsScored(homeTeam.getGoalsScored() + match.getHomeTeamScored());
            homeTeam.setGoalsAgainst(homeTeam.getGoalsAgainst() + match.getAwayTeamScored());
            awayTeam.setGoalsScored(awayTeam.getGoalsScored() + match.getAwayTeamScored());
            awayTeam.setGoalsAgainst(awayTeam.getGoalsAgainst() + match.getHomeTeamScored());
            homeTeam.setMatchesPlayed(homeTeam.getMatchesPlayed() + 1);
            awayTeam.setMatchesPlayed(awayTeam.getMatchesPlayed() + 1);

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
