package com.onthepitch.backend.soccerApi;

import com.onthepitch.backend.model.Club;
import com.onthepitch.backend.model.League;
import com.onthepitch.backend.model.Season;
import com.onthepitch.backend.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoccerDataServiceImpl implements SoccerDataService {
    @Autowired
    private SeasonService seasonService;

    @Override
    public Season save(Season season) {
        seasonService.saveOrUpdate(season);
        return season;
    }

    @Override
    public List<Season> seasons(int id) {
        return seasonService.load(id);
    }
}
