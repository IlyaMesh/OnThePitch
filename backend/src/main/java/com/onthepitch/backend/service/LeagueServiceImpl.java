package com.onthepitch.backend.service;

import com.onthepitch.backend.repos.LeagueRepository;
import com.onthepitch.backend.model.League;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeagueServiceImpl implements LeagueService {

    private LeagueRepository leagueRepository;

    @Autowired
    public LeagueServiceImpl(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    @Override
    public List<League> getAll() {
        List<League> leagues = new ArrayList<>();
        leagueRepository.findAll().forEach(leagues::add);
        return leagues;
    }

    @Override
    public League getById(Long id) {
      //  List<League> leagues = listAll();
       // return leagues.stream().filter(league -> league.getLeague_id().equals(id)).findFirst().get();
        //return leagueRepository.findByLeague_id(id);
        return leagueRepository.findById(id).get();
    }

    @Override
    public League saveOrUpdate(League league) {
       leagueRepository.save(league);
       return league;
    }
}
