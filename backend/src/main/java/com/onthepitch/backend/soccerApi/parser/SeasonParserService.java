package com.onthepitch.backend.soccerApi.parser;

import com.onthepitch.backend.model.Season;

import java.util.List;

public interface SeasonParserService {
    List<Season> toSeasons(String jsonString);

    Season toCurrentSeason(String jsonString);
}
