package com.onthepitch.backend.repos;

import com.onthepitch.backend.model.Log;
import com.onthepitch.backend.repos.query.QueryUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LogRepository extends JpaRepository<Log, Long> {

    @Query(QueryUtils.FIND_ALL_LOGS)
    Page<Log> findAll(Pageable pageable);
}
