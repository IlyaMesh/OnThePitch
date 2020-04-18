package com.onthepitch.backend.controllers;

import com.onthepitch.backend.service.MatchService;
import com.onthepitch.shared.model.response.MatchesResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class for matches controllers
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MatchesController {
    private final MatchService matchService;

    @Autowired
    public MatchesController(MatchService matchService) {
        this.matchService = matchService;
    }

    /**
     * Controller method for getting nearest matches
     *
     * @return List of the objects, that contains data about matches.
     */
    @GetMapping("/matches")
    public Page<MatchesResult> listMatches(@RequestParam(defaultValue = "0") int page, @RequestParam("size") int size) {
        return matchService.listAll(page, size);
    }

    @GetMapping("/matches/favourite")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('MODERATOR') or hasAuthority('ADMIN')")
    public Page<MatchesResult> getListFavouriteMatches(@RequestParam(defaultValue = "0") int page, @RequestParam("size") int size) {
        return matchService.getFavouriteTeamMatches(page, size);
    }

}
