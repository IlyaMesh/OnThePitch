package com.onthepitch.backend.dao;

import com.onthepitch.backend.model.League;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface LeagueRepository extends CrudRepository<League,Long> {
  // League findByLeague_id(Long id);
}
