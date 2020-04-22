package com.onthepitch.backend.repos;

import com.onthepitch.backend.model.Rating;
import com.onthepitch.backend.model.User;
import com.onthepitch.backend.repos.query.QueryUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query(QueryUtils.FIND_RATING_BY_NOTE)
    List<Rating> getAllByNote_id(@Param("id") Long id);

    @Query(QueryUtils.FIND_RATING_BY_USER_AND_NOTE)
    Optional<Rating> findRateByUserAndNote(@Param("user") User user, @Param("note_id") Long note_id);

    @Modifying
    @Query(QueryUtils.DELETE_RATING_BY_USER_AND_NOTE)
    void deleteByUserAndNote_id(@Param("user") User user, @Param("note_id") Long note_id);

    @Modifying
    @Query(QueryUtils.DELETE_RATING_BY_NOTE)
    void deleteByNote_id(@Param("note_id") Long note_id);
}
