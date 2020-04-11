package com.onthepitch.backend.soccerApi;

import org.springframework.stereotype.Service;

@Service
public class EndpointProviderServiceImpl implements EndpointProviderService {
    private static final String BASE_URL = "http://api.football-data.org/v2/";


    private String getCompetitions() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_URL).append("competitions/");
        return stringBuilder.toString();
    }

    @Override
    public String getCompetition(int competitionId) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getCompetitions()).append(competitionId).append("/");
        return stringBuilder.toString();
    }


    @Override
    public String getTeams(int competitionId) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getCompetition(competitionId)).append("teams/");
        return stringBuilder.toString();
    }

    @Override
    public String getSeasonMatches(int competitionId) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getCompetition(competitionId)).append("matches?season=2019");
        return stringBuilder.toString();
    }

    @Override
    public String getMatch(int matchId) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_URL).append("matches/").append(matchId);
        return stringBuilder.toString();
    }


}
