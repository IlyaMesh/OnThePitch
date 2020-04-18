package com.onthepitch.backend.service.serviceImpl;

import com.onthepitch.backend.converter.MatchToResult;
import com.onthepitch.backend.model.User;
import com.onthepitch.backend.repos.MatchRepository;
import com.onthepitch.backend.model.Match;
import com.onthepitch.backend.service.LeagueService;
import com.onthepitch.backend.service.MatchService;
import com.onthepitch.backend.service.SeasonService;
import com.onthepitch.backend.soccerApi.EndpointProviderService;
import com.onthepitch.backend.soccerApi.RestClientService;
import com.onthepitch.backend.soccerApi.parser.MatchParserService;
import com.onthepitch.shared.model.response.MatchesResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private MatchToResult matchToResult;

    @Override
    public List<Match> getFromApi(int compId) {
        String endpoint = endpointProviderService.getSeasonMatches(Math.toIntExact(compId));
        String s = restClientService.get(endpoint);
        return parser.toMatches(s);
    }

    private Match getMatch(int match_id){
        String match = endpointProviderService.getMatch(match_id);
        String s = restClientService.get(match);
        return parser.getMatch(s);
    }

    @Override
    public void saveOrUpdate(Match match) {
        matchRepository.save(match);
    }

    @Override
    public void updateMatches() throws InterruptedException {
        Date date = new Date();
        List<Match> matchesNeedToBeUpdated = matchRepository.findMatchesNeedToBeUpdated(date);//матчи из бд, взять их айдишники и найти каждый матч через api, данные обновить
        int i =0;
        for(Match match: matchesNeedToBeUpdated){
            if(i == 8){
                Thread.sleep(1002);
                i=0;
            }
            Long match_id = match.getMatch_id();
            Match matchInfo = getMatch(Math.toIntExact(match_id));
            if(matchInfo == null)
                continue;
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

    @Override
    public Page<MatchesResult> listAll(int page, int size) {
        //soccerDataService.updateAll(); Т.к. обновлять данные пока нет смысла из-за пандемии, а можно сделать только 10 запросов
        Date from = Date.from(LocalDate.now().minusDays(6).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date to = Date.from(LocalDate.now().plusDays(6).atStartOfDay(ZoneId.systemDefault()).toInstant());
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Match> matches = matchRepository.findMatchesByMatchTimeBetweenOrderByMatchTime(from, to, pageRequest);
        int totalElements = (int) matches.getTotalElements();
        return new PageImpl<>(matches.stream()
                .map(match -> matchToResult.convert(match))
                .collect(Collectors.toList()), pageRequest, totalElements);
    }

    @Override
    public Page<MatchesResult> getFavouriteTeamMatches(int page, int size) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Match> matches = matchRepository.findMatchesOfFavouriteTeam(user.getClub_id(),pageRequest);
        int totalElements = (int) matches.getTotalElements();
        return new PageImpl<>(matches.stream()
                .map(match -> matchToResult.convert(match))
                .collect(Collectors.toList()), pageRequest, totalElements);
    }
}
