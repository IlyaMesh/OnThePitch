package com.onthepitch.backend.repos;

import com.onthepitch.backend.model.League;
import com.onthepitch.backend.model.Match;
import com.onthepitch.backend.model.Season;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface MatchRepository extends CrudRepository<Match,Long> {


    Page<Match> findMatchesByMatchTimeBetweenOrderByMatchTime(Date from, Date to,Pageable pageable);

    @Query(value = "SELECT m FROM Match m where m.lastUpdated < m.matchTime and m.matchTime<:today")
    List<Match> findMatchesNeedToBeUpdated(@Param("today")Date today);

    @Query(value="SELECT m from Match m where m.homeTeamScored is not null and m.awayTeamScored is not null and m.league = :league and m.season = :season")
    List<Match> findMatchesThatGone(@Param("league") League league,@Param("season") Season season);

    @Query(value = "select m from Match m where m.homeTeam.club_id = :favTeamId or m.awayTeam.club_id = :favTeamId")
    Page<Match> findMatchesOfFavouriteTeam(@Param("favTeamId") Long id, Pageable pageable);
}
