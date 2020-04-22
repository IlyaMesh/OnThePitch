package com.onthepitch.backend.repos;

import com.onthepitch.backend.model.League;
import com.onthepitch.backend.model.Match;
import com.onthepitch.backend.model.Season;
import com.onthepitch.backend.repos.query.QueryUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface MatchRepository extends CrudRepository<Match,Long> {


    Page<Match> findMatchesByMatchTimeBetweenOrderByMatchTime(Date from, Date to,Pageable pageable);

    @Query(value = QueryUtils.FIND_MATCHES_NEEDED_TO_UPDATE)
    List<Match> findMatchesNeedToBeUpdated(@Param("today")Date today);

    @Query(value= QueryUtils.FIND_MATCH_THAT_GONE)
    List<Match> findMatchesThatGone(@Param("league") League league,@Param("season") Season season);

    @Query(value = QueryUtils.FIND_MATCHES_OF_FAV_TEAM)
    Page<Match> findMatchesOfFavouriteTeam(@Param("favTeamId") Long id, Pageable pageable);
}
