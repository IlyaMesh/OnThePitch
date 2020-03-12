package com.onthepitch.backend.controllers;

import com.onthepitch.backend.commands.StandingsTeam;
import com.onthepitch.backend.converter.StandingsToModel;
import com.onthepitch.backend.service.StandingsService;
import com.onthepitch.shared.model.StandingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class StandingsController {
    @Autowired
    public StandingsToModel standingsToModel;
    @Autowired
    public StandingsService standingsService;

    @GetMapping("/standings/{id}")
    public List<StandingModel> getStandings(@PathVariable String id){
        List<StandingsTeam> standings = standingsService.getStandings(Long.valueOf(id));
        List<StandingModel> standingModels = new ArrayList<>();
        for (StandingsTeam team : standings){
            standingModels.add(standingsToModel.convert(team));
        }
        return standingModels;
    }
}
