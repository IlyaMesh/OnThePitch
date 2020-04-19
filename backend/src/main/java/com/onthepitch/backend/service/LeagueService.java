package com.onthepitch.backend.service;

import com.onthepitch.backend.model.League;

import java.util.List;

public interface LeagueService {

    List<League> getAll();

    League getById(Long id);

    League saveOrUpdate(League league);
}
