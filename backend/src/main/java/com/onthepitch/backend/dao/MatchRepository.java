package com.onthepitch.backend.dao;

import com.onthepitch.backend.model.Match;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<Match,Long> {
}
