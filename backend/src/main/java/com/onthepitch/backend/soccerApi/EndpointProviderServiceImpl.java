package com.onthepitch.backend.soccerApi;

import org.springframework.stereotype.Service;

@Service
public class EndpointProviderServiceImpl implements EndpointProviderService {
    private static final String BASE_URL = "http://api.football-data.org/v2/";


    private String getBase() {
        return BASE_URL;
    }


    private String getCompetitions() {
        return BASE_URL + "competitions/";
    }

    @Override
    public String getCompetition(int competitionId) {
        return getCompetitions() + competitionId + "/";
    }


    @Override
    public String getTeams(int competitionId) {
        return getCompetition(competitionId) + "teams/";
    }

}
