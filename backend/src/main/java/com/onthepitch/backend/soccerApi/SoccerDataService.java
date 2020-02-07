package com.onthepitch.backend.soccerApi;

import com.onthepitch.backend.model.Club;
import com.onthepitch.backend.model.League;
import com.onthepitch.backend.model.Season;

import java.util.List;

public interface SoccerDataService {
    Season save(Season season);

    List<Season> seasons(int id);
}
