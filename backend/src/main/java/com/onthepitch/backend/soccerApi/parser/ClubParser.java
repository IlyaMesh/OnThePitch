package com.onthepitch.backend.soccerApi.parser;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.onthepitch.backend.model.Club;
import com.onthepitch.backend.soccerApi.JsonExtractor;

import java.util.ArrayList;
import java.util.List;

public class ClubParser {
    private final JsonParser parser = new JsonParser();
    private final JsonExtractor extractor = new JsonExtractor();

    public List<Club> getClubs(String jsonString){
        JsonElement element = parser.parse(jsonString);
        JsonObject json = element.getAsJsonObject();
        return parseClubs(json);
    }
    private List<Club> parseClubs(JsonObject json) {
        List<Club> clubs = new ArrayList<>();
        //Integer id = extractor.extractInt(json, "id");
        List<JsonObject> clubsJson = extractor.extractList(json, "teams");
        clubsJson.forEach(item -> clubs.add(parseClub(item)));
        return clubs;
    }

    private Club parseClub(JsonObject item) {
        Club club = new Club();
        club.setClub_id((long) parseId(item));
        club.setClub_name(parseName(item));
        club.setLogo(parseLogo(item));
        club.setStadium(parseStadium(item));
        club.setClub_info("Founded " + parseFounded(item) + "\nClubColors " + parseColors(item));
        return club;
    }

    private int parseId(JsonObject json) {
        return extractor.extractInt(json, "id");
    }

    private String parseName(JsonObject json) {
        return extractor.extractString(json, "name");
    }

    private String parseLogo(JsonObject json) {
        return extractor.extractString(json, "crestUrl");
    }

    private String parseStadium(JsonObject json) {
        return extractor.extractString(json, "venue");
    }

    private int parseFounded(JsonObject json) {
        return extractor.extractInt(json, "founded");
    }

    private String parseColors(JsonObject json) {
        return extractor.extractString(json, "clubColors");
    }
}
