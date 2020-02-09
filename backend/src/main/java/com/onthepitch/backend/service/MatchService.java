package com.onthepitch.backend.service;

import com.onthepitch.backend.model.Match;

import java.util.Date;
import java.util.List;

public interface MatchService {
    List<Match> loadFinished(int id);

    List<Match> getFromApi(int compId);

    Match saveOrUpdate(Match match);

    List<Match> getNonUpdatedMatches(Date date);
}
