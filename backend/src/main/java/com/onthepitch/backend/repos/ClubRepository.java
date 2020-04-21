package com.onthepitch.backend.repos;

import com.onthepitch.backend.model.Club;
import com.onthepitch.backend.repos.query.QueryUtils;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ClubRepository extends CrudRepository<Club,Long> {

    @Query(value = QueryUtils.FIND_All_CLUBS)
    Iterable<Club> findAllAndAndOOrderByClub_name();
}
