package com.onthepitch.backend.soccerApi.parser;

import com.onthepitch.backend.model.Match;

import java.util.List;

public interface MatchParserService {
    List<Match> toMatches(String json);
}
