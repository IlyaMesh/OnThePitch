package com.onthepitch.backend.soccerApi;

import com.onthepitch.backend.model.Club;
import com.onthepitch.backend.model.League;
import com.onthepitch.backend.model.Match;
import com.onthepitch.backend.model.Season;
import com.onthepitch.backend.service.ClubService;
import com.onthepitch.backend.service.MatchService;
import com.onthepitch.backend.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SoccerDataServiceImpl implements SoccerDataService {
    @Autowired
    private SeasonService seasonService;
    @Autowired
    private ClubService clubService;
    @Autowired
    private MatchService matchService;

    @Override
    public Season save(Season season) {
        seasonService.saveOrUpdate(season);
        return season;
    }

    @Override
    public Club save(Club club) {
        clubService.saveOrUpdate(club);
        return club;
    }

    @Override
    public List<Season> seasons(int id) {
        return seasonService.load(id);
    }

    @Override
    public Season getCurrentSeason(int id) {
        return seasonService.loadCurrent(id);
    }

    @Override
    public List<Club> clubs(int parseInt) {
        return clubService.getFromApi((long) parseInt);
    }

    @Override
    public List<Match> matches(int compId) {
        return matchService.getFromApi(compId);
    }

    @Override
    public Match save(Match match) {
        matchService.saveOrUpdate(match);
        return match;
    }

    @Override
    public void updateAll() {
        //check if this season ended - upload new teams,matches and seaons. Update all matches results
        //first: matches
        List<Match> nonUpdatedMatches = matchService.getNonUpdatedMatches(new Date());

    }
}
