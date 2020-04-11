package com.onthepitch.backend.soccerApi.parser;

import com.onthepitch.backend.model.Club;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubParserServiceImpl implements ClubParserService {
    private final ClubParser clubParser= new ClubParser();

    @Override
    public List<Club> toClubs(String jsonString) {
        return clubParser.getClubs(jsonString);
    }
}
