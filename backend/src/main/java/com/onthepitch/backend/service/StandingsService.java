package com.onthepitch.backend.service;

import com.onthepitch.backend.commands.StandingsTeam;

import java.util.List;

public interface StandingsService {
    List<StandingsTeam> getStandings(Long leagueId);
}
