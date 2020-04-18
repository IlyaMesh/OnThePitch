package com.onthepitch.backend.service;

import com.onthepitch.backend.model.Match;
import com.onthepitch.shared.model.response.MatchesResult;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MatchService {

    List<Match> getFromApi(int compId);

    void saveOrUpdate(Match match);

    void updateMatches() throws InterruptedException;

    List<Match> getMatchesInLeagueAndCurrentSeason(Long league_id);

    Page<MatchesResult> listAll(int page, int size);

    Page<MatchesResult> getFavouriteTeamMatches(int page, int size);

}
