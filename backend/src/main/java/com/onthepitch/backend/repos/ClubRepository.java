package com.onthepitch.backend.repos;

import com.onthepitch.backend.model.Club;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ClubRepository extends CrudRepository<Club,Long> {

    @Query(value = "select c from Club c order by c.club_name")
    Iterable<Club> findAllAndAndOOrderByClub_name();
}
