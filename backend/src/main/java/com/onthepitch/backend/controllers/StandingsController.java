package com.onthepitch.backend.controllers;

import com.onthepitch.backend.commands.StandingsTeam;
import com.onthepitch.backend.converter.StandingsToModel;
import com.onthepitch.backend.model.League;
import com.onthepitch.backend.service.LeagueService;
import com.onthepitch.backend.service.StandingsService;
import com.onthepitch.shared.model.StandingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for standings controllers
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class StandingsController {

    private StandingsToModel standingsToModel;
    private LeagueService leagueService;
    private StandingsService standingsService;

    @Autowired
    public StandingsController(StandingsToModel standingsToModel, StandingsService standingsService, LeagueService leagueService) {
        this.standingsToModel = standingsToModel;
        this.standingsService = standingsService;
        this.leagueService = leagueService;
    }

    /**
     * Controller method for getting standings data
     *
     * @param id That's league id
     * @return List of the objects, that contains data about points,goals etc.
     */
    @GetMapping("/standings/{id}")
    public List<StandingModel> getStandings(@PathVariable String id) {
        List<StandingsTeam> standings = standingsService.getStandings(Long.valueOf(id));
        List<StandingModel> standingModels = new ArrayList<>();
        for (StandingsTeam team : standings) {
            standingModels.add(standingsToModel.convert(team));
        }
        return standingModels;
    }

    @GetMapping("/standings/leagues")
    public List<League> getLeagues() {
        return leagueService.getAll();
    }
}
