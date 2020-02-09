package com.onthepitch.backend.dao;

import com.onthepitch.backend.model.Match;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface MatchRepository extends CrudRepository<Match,Long> {
    //TODO поменять запрос, необходимо выводить ближайщие матчи к сегодняшнему дню во всех лигах
    //@Query(value = "SELECT m FROM Match m where m.league.league_id = 2019 and m.homeTeam.club_id = 586")
   // @Query(value = "SELECT m FROM Match m where m.matchTime = :today")
    //List<Match> findUpcomingMatches( @Param("from") Date from,@Param("to") Date to);

    List<Match> findMatchesByMatchTimeBetween(Date from, Date to);

    @Query(value = "SELECT m FROM Match m where m.lastUpdated < m.matchTime and m.matchTime<:today")
    List<Match> findMatchesNeedToBeUpdatet(@Param("today")Date today);
}
