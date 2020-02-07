package com.onthepitch.backend.dao;

import com.onthepitch.backend.model.Season;
import org.springframework.data.repository.CrudRepository;

public interface SeasonRepository extends CrudRepository<Season,Long> {
}
