package com.onthepitch.backend.service;

import com.onthepitch.backend.dao.MatchRepository;
import com.onthepitch.backend.model.Club;
import com.onthepitch.backend.model.Match;
import com.onthepitch.backend.soccerApi.EndpointProviderService;
import com.onthepitch.backend.soccerApi.RestClientService;
import com.onthepitch.backend.soccerApi.parser.ClubParserService;
import com.onthepitch.backend.soccerApi.parser.MatchParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class MatchServiceImpl implements MatchService {
    @Autowired
    private MatchParserService parser;
    @Autowired
    private EndpointProviderService endpointProviderService;
    @Autowired
    private RestClientService restClientService;
    @Autowired
    private MatchRepository matchRepository;
    @Override
    public List<Match> loadFinished(int id) {
        return null;
    }

    @Override
    public List<Match> getFromApi(int compId) {
        String endpoint = endpointProviderService.getSeasonMatches(Math.toIntExact(compId));
        String s = restClientService.get(endpoint);
        List<Match> matches = parser.toMatches(s);
        return matches;
    }

    @Override
    public Match saveOrUpdate(Match match) {
        matchRepository.save(match);
        return match;
    }

    @Override
    public List<Match> getNonUpdatedMatches(Date date) {
        List<Match> matchesNeedToBeUpdatet = matchRepository.findMatchesNeedToBeUpdatet(date);
        return null;
    }
}
