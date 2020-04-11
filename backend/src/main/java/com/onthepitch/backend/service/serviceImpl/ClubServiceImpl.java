package com.onthepitch.backend.service.serviceImpl;

import com.onthepitch.backend.commands.ClubForm;
import com.onthepitch.backend.converter.ClubFormToClub;
import com.onthepitch.backend.repos.ClubRepository;
import com.onthepitch.backend.model.Club;
import com.onthepitch.backend.service.ClubService;
import com.onthepitch.backend.soccerApi.EndpointProviderService;
import com.onthepitch.backend.soccerApi.RestClientService;
import com.onthepitch.backend.soccerApi.parser.ClubParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClubServiceImpl implements ClubService {

    private ClubParserService parser;
    private EndpointProviderService endpointProviderService;
    private RestClientService restClientService;
    private final ClubRepository clubRepository;
    private final ClubFormToClub clubFormToClub;

    @Autowired
    public ClubServiceImpl(ClubParserService parser,EndpointProviderService endpointProviderService,RestClientService restClientService,ClubRepository clubRepository, ClubFormToClub clubFormToClub) {
        this.parser = parser;
        this.endpointProviderService =endpointProviderService;
        this.restClientService = restClientService;
        this.clubRepository = clubRepository;
        this.clubFormToClub = clubFormToClub;
    }

    @Override
    public List<Club> listAll() {
        List<Club> clubs = new ArrayList<>();
        clubRepository.findAll().forEach(clubs::add);
        return clubs;
    }

    @Override
    public Club getById(Long id) {
        return clubRepository.findById(id).get();
    }

    @Override
    public Club saveOrUpdate(Club club) {
        clubRepository.save(club);
        return club;
    }

    @Override
    public void delete(Long id) {
    clubRepository.deleteById(id);
    }

    @Override
    public Club saveOrUpdatePostForm(ClubForm clubForm) {
        Club savedClub = saveOrUpdate(clubFormToClub.convert(clubForm));
        System.out.println("Saved product id+ "+savedClub.getClub_id());
        return savedClub;
    }

    @Override
    public List<Club> getFromApi(Long id) {
        String endpoint = endpointProviderService.getTeams(Math.toIntExact(id));
        String s = restClientService.get(endpoint);
        return parser.toClubs(s);
    }
}
