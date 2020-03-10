package com.onthepitch.backend.service;

import com.onthepitch.backend.dao.MatchRepository;
import com.onthepitch.backend.model.Match;
import com.onthepitch.backend.soccerApi.EndpointProviderService;
import com.onthepitch.backend.soccerApi.RestClientService;
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
    @Autowired
    private SeasonService seasonService;
    @Autowired
    private LeagueService leagueService;
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

    private Match getMatch(int match_id){
        String match = endpointProviderService.getMatch(match_id);
        String s = restClientService.get(match);
        Match match1 = parser.getMatch(s);
        return match1;
    }

    @Override
    public Match saveOrUpdate(Match match) {
        matchRepository.save(match);
        return match;
    }

    @Override
    public void updateMatches() throws InterruptedException {
        Date date = new Date();
        List<Match> matchesNeedToBeUpdated = matchRepository.findMatchesNeedToBeUpdated(date);//матчи из бд, взять их айдишники и найти каждый матч через api, данные обновить
        int i =0;
        for(Match match: matchesNeedToBeUpdated){
            if(i == 9){
                Thread.sleep(60001);
                i=0;
            }
            Long match_id = match.getMatch_id();
            Match matchInfo = getMatch(Math.toIntExact(match_id));
            if(matchInfo == null){
                i++;
                continue;

            }

            match.setHomeTeamScored(matchInfo.getHomeTeamScored());
            match.setAwayTeamScored(matchInfo.getAwayTeamScored());
            match.setAwayTeamPenalties(matchInfo.getAwayTeamPenalties());
            match.setHomeTeamPenalties(matchInfo.getHomeTeamPenalties());
            match.setMatchTime(matchInfo.getMatchTime());
            match.setLastUpdated(date);
            matchRepository.save(match);
            String t = "";
            i++;
        }


    }

    @Override
    public List<Match> getMatchesInLeagueAndCurrentSeason(Long league_id) {
        return matchRepository.findMatchesThatGone(leagueService.getById(league_id),seasonService.loadCurrent(Math.toIntExact(league_id)));

    }
}
