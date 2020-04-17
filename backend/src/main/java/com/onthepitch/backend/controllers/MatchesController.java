package com.onthepitch.backend.controllers;

import com.onthepitch.backend.converter.MatchToResult;
import com.onthepitch.backend.model.Match;
import com.onthepitch.backend.repos.MatchRepository;
import com.onthepitch.shared.model.response.MatchesResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Class for matches controllers
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MatchesController {

    private final MatchToResult matchToResult;
    private final MatchRepository matchRepository;

    @Autowired
    public MatchesController(MatchToResult matchToResult, MatchRepository matchRepository) {
        this.matchToResult = matchToResult;
        this.matchRepository = matchRepository;
    }

    /**
     * Controller method for getting nearest matches
     *
     * @return List of the objects, that contains data about matches.
     */
    @GetMapping("/matches")
    public Page<MatchesResult> listMatches(@RequestParam(defaultValue = "0") int page,@RequestParam("size")int size) {
//Problem with postponed matches need to be solved
        //        try{
//        soccerDataService.updateAll();}
//        catch (InterruptedException ex){
//            System.out.println(ex.getMessage());
//        }\
        Date from = Date.from(LocalDate.now().minusDays(10).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date to = Date.from(LocalDate.now().plusDays(6).atStartOfDay(ZoneId.systemDefault()).toInstant());
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Match> matches = matchRepository.findMatchesByMatchTimeBetweenOrderByMatchTime(from, to, pageRequest);
        int totalElements = (int) matches.getTotalElements();
        return new PageImpl<>(matches.stream()
                .map(match -> matchToResult.convert(match))
                .collect(Collectors.toList()), pageRequest, totalElements);
    }
}
