package com.onthepitch.backend.controllers;

import com.onthepitch.backend.converter.MatchToResult;
import com.onthepitch.backend.dao.MatchRepository;
import com.onthepitch.backend.model.Match;
import com.onthepitch.backend.soccerApi.SoccerDataService;
import com.onthepitch.shared.model.MatchesResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class for matches controllers
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MatchesController {

    private MatchToResult matchToResult;
    private SoccerDataService soccerDataService;
    private MatchRepository matchRepository;

    @Autowired
    public MatchesController(MatchToResult matchToResult, MatchRepository matchRepository,SoccerDataService soccerDataService) {
        this.matchToResult = matchToResult;
        this.soccerDataService = soccerDataService;
        this.matchRepository = matchRepository;
    }

    /**
     * Controller method for getting nearest matches
     * @return List of the objects, that contains data about matches.
     */
    @GetMapping("/matches")
    public List<MatchesResult> listMatches() {
//Problem with postponed matches need to be solved
        //        try{
//        soccerDataService.updateAll();}
//        catch (InterruptedException ex){
//            System.out.println(ex.getMessage());
//        }
        Date from = Date.from(LocalDate.now().minusDays(4).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date to = Date.from(LocalDate.now().plusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<Match> matches = matchRepository.findMatchesByMatchTimeBetweenOrderByMatchTime(from, to);
        List<MatchesResult> matchesResults = new ArrayList<MatchesResult>();
        for (Match match : matches) {
            matchesResults.add(matchToResult.convert(match));
        }
        return matchesResults;
    }
}