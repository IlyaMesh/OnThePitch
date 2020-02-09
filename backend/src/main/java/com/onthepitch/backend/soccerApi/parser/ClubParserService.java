package com.onthepitch.backend.soccerApi.parser;

import com.onthepitch.backend.model.Club;

import java.util.List;

public interface ClubParserService {
    List<Club> toClubs(String jsonString);
}
