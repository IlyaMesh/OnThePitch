package com.onthepitch.backend.service.serviceImpl;

import com.onthepitch.backend.repos.SeasonRepository;
import com.onthepitch.backend.model.Season;
import com.onthepitch.backend.service.LeagueService;
import com.onthepitch.backend.service.SeasonService;
import com.onthepitch.backend.soccerApi.EndpointProviderService;
import com.onthepitch.backend.soccerApi.RestClientService;
import com.onthepitch.backend.soccerApi.parser.SeasonParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SeasonServiceImpl implements SeasonService {
    @Autowired
    private SeasonParserService parser;
    @Autowired
    private SeasonRepository seasonRepository;
    @Autowired
    private EndpointProviderService endpointProviderService;
    @Autowired
    private RestClientService restClientService;
    @Autowired
    private LeagueService leagueService;


    @Override
    public Season loadCurrent(int id) {
       // List<Season> seasons = load(id);
        List<Season> seasonsByLeague = seasonRepository.findSeasonsByLeague(leagueService.getById((long) id));
        Date today = new Date();
        return seasonsByLeague.stream()
                .filter(season -> season.getStart_date().before(today) && season.getEnd_date().after(today)).findFirst().get();
    }

    @Override
    public List<Season> load(int id) {

        String endpoint = endpointProviderService.getCompetition(id);
        String s = restClientService.get(endpoint);
        List<Season> seasons = parser.toSeasons(s);
        for (Season season : seasons) {
            season.setLeague(leagueService.getById((long) id));
        }
        return seasons;
    }

    @Override
    public void saveOrUpdate(Season season) {
        seasonRepository.save(season);

    }

    @Override
    public Season getById(Long id) {
        return seasonRepository.findById(id).get();
    }

}
