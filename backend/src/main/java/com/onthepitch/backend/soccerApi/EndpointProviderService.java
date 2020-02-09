package com.onthepitch.backend.soccerApi;

public interface EndpointProviderService {




    String getCompetition(int competitionId);


 String getTeams(int competitionId);

String getSeasonMatches(int competitionId);
}
