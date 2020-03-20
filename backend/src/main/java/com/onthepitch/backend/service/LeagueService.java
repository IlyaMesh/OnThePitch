package com.onthepitch.backend.service;

import com.onthepitch.backend.model.League;

import java.util.List;

public interface LeagueService {
    public List<League> getAll();
    League getById(Long id);
    League saveOrUpdate(League league);
}
