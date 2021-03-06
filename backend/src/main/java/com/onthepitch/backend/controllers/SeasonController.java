package com.onthepitch.backend.controllers;


import com.onthepitch.backend.model.Club;
import com.onthepitch.backend.model.Match;
import com.onthepitch.backend.model.Season;
import com.onthepitch.backend.soccerApi.SoccerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class SeasonController {

    public SoccerDataService soccerDataService;


    @Autowired
    public SeasonController(SoccerDataService soccerDataService) {
        this.soccerDataService = soccerDataService;
    }

    @GetMapping("/competitions/{id}/seasons")
    public List<Season> leagueSeasons(@PathVariable String id){
        List<Season> seasons = soccerDataService.seasons(Integer.parseInt(id));
        for(Season season : seasons){
        soccerDataService.save(season);
        }
        return seasons;
    }

    @GetMapping("/competitions/{id}/teams")
    public List<Club> getClubs(@PathVariable String id){
        List<Club> clubs = soccerDataService.clubs(Integer.parseInt(id));
        for(Club club : clubs){
            soccerDataService.save(club);
        }
        return clubs;
    }

    @GetMapping("/competitions/{id}/matches")
    public List<Match> getMatches(@PathVariable String id){
        List<Match> matches = soccerDataService.matches(Integer.parseInt(id));
        for(Match match: matches){
            soccerDataService.save(match);
        }
        return matches;
    }
}
