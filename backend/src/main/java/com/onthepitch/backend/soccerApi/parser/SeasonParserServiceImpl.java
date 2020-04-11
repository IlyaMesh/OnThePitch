package com.onthepitch.backend.soccerApi.parser;

import com.onthepitch.backend.model.Season;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SeasonParserServiceImpl implements SeasonParserService {
    private final SeasonParser seasonParser = new SeasonParser();
    @Override
    public List<Season> toSeasons(String jsonString) {
        return seasonParser.getSeasons(jsonString);
    }

    @Override
    public Season toCurrentSeason(String jsonString) {
        List<Season> seasons = seasonParser.getSeasons(jsonString);
        Date today = new Date();
        return seasons.stream()
                .filter(season -> season.getStart_date().before(today) && season.getEnd_date().after(today)).findFirst().get();
    }
}
