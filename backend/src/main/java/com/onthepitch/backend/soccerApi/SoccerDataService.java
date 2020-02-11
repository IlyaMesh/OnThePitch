package com.onthepitch.backend.soccerApi;

import com.onthepitch.backend.model.Club;
import com.onthepitch.backend.model.League;
import com.onthepitch.backend.model.Match;
import com.onthepitch.backend.model.Season;

import java.util.List;

public interface SoccerDataService {
    Season save(Season season);

    Club save(Club club);

    List<Season> seasons(int id);

    Season getCurrentSeason(int id);

    List<Club> clubs(int parseInt);

    List<Match> matches(int parseInt);

    Match save(Match match);

    void updateAll() throws InterruptedException;
}
