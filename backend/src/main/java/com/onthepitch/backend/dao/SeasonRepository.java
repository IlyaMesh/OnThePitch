package com.onthepitch.backend.dao;

import com.onthepitch.backend.model.League;
import com.onthepitch.backend.model.Season;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SeasonRepository extends CrudRepository<Season,Long> {

    List<Season> findSeasonsByLeague(League league);
}
