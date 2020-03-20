package com.onthepitch.backend.repos;

import com.onthepitch.backend.model.Club;
import org.springframework.data.repository.CrudRepository;

public interface ClubRepository extends CrudRepository<Club,Long> {
}
