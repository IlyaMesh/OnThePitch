package com.onthepitch.backend.service;

import com.onthepitch.backend.model.Season;

import java.util.List;

public interface SeasonService {
    Season loadCurrent(int id);
    List<Season> load(int id);
    Season saveOrUpdate(Season season);
    Season getById(Long id);
}
