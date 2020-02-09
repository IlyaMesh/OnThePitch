package com.onthepitch.backend.soccerApi.parser;

import com.onthepitch.backend.model.Club;
import com.onthepitch.backend.model.League;
import com.onthepitch.backend.model.Match;
import com.onthepitch.backend.model.Season;
import com.onthepitch.backend.service.ClubService;
import com.onthepitch.backend.service.LeagueService;
import com.onthepitch.backend.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchParserServiceImpl implements MatchParserService {
    private MatchParser matchParser = new MatchParser();
    @Autowired
    private ClubService clubService;
    @Autowired
    private LeagueService leagueService;
    @Autowired
    private SeasonService seasonService;

    @Override
    public List<Match> toMatches(String json) {
        List<Match> matches = matchParser.getMatches(json);
        //заменить все поля на взятые из бд
        for(Match match : matches){
            Long club_id = match.getHomeTeam().getClub_id();
            Club club = clubService.getById(club_id);
            match.setHomeTeam(club);

            club_id = match.getAwayTeam().getClub_id();
            Club away = clubService.getById(club_id);
            match.setAwayTeam(away);

            Long league_id = match.getLeague().getLeague_id();
            League byId = leagueService.getById(league_id);
            match.setLeague(byId);

            Long season_id = match.getSeason().getSeason_id();
            Season season = seasonService.getById(season_id);
            match.setSeason(season);
        }
        return matches;
    }
}
