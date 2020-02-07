package com.onthepitch.backend.controllers;

import com.onthepitch.backend.model.Season;
import com.onthepitch.backend.soccerApi.SoccerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class SeasonController {
    @Autowired
    public SoccerDataService soccerDataService;

    @GetMapping("/competitions/{id}/seasons")
    public List<Season> leagueSeasons(@PathVariable String id){
        List<Season> seasons = soccerDataService.seasons(Integer.parseInt(id));
        for(Season season : seasons){
        soccerDataService.save(season);
        }
        return seasons;
    }
}
