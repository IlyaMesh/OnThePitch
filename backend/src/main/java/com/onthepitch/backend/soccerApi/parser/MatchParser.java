package com.onthepitch.backend.soccerApi.parser;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.onthepitch.backend.model.Club;
import com.onthepitch.backend.model.League;
import com.onthepitch.backend.model.Match;
import com.onthepitch.backend.model.Season;
import com.onthepitch.backend.soccerApi.JsonExtractor;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MatchParser {
    private final JsonParser parser = new JsonParser();
    private final JsonExtractor extractor = new JsonExtractor();

    public List<Match> getMatches(String jsonString) {
        JsonElement element = parser.parse(jsonString);
        JsonObject json = element.getAsJsonObject();
        return parseMatches(json);
    }

    public Match getMatchInfo(String jsonString){
        JsonElement element = parser.parse(jsonString);
        JsonObject json = element.getAsJsonObject();
        JsonObject match = extractor.extractJson(json,"match");
        if(!parseStatus(match).equals("FINISHED")){
            return null;
        }
        Match matchWrapper = new Match();
        matchWrapper.setMatch_id((long) parseId(match));
        matchWrapper.setHomeTeamScored(extractor.extractInt(parseTeamScore(match),"homeTeam"));
        matchWrapper.setAwayTeamScored(extractor.extractInt(parseTeamScore(match),"awayTeam"));
        matchWrapper.setHomeTeamPenalties(extractor.extractInt(parseTeamPenalties(match),"homeTeam"));
        matchWrapper.setAwayTeamPenalties(extractor.extractInt(parseTeamPenalties(match),"awayTeam"));

        return matchWrapper;
    }

    private List<Match> parseMatches(JsonObject json) {
        List<Match> matches = new ArrayList<>();
        //Integer id = extractor.extractInt(json, "id");
        JsonObject competition = extractor.extractJson(json, "competition");
        Integer id = parseId(competition);
        List<JsonObject> matchesJson = extractor.extractList(json, "matches");
        matchesJson.forEach(item -> matches.add(parseMatch(item,id)));
        return matches;
    }

    private Match parseMatch(JsonObject item, Integer compId) {
        Match match = new Match();
        match.setMatch_id((long) parseId(item));
        Club homeTeam = new Club();
        homeTeam.setClub_id((long) parseIdHomeClub(item));
        match.setHomeTeam(homeTeam);
        Club awayTeam = new Club();
        awayTeam.setClub_id((long) parseIdAwayClub(item));
        match.setAwayTeam(awayTeam);
        match.setHomeTeamScored(extractor.extractInt(parseTeamScore(item),"homeTeam"));
        match.setAwayTeamScored(extractor.extractInt(parseTeamScore(item),"awayTeam"));
        match.setHomeTeamPenalties(extractor.extractInt(parseTeamPenalties(item),"homeTeam"));
        match.setAwayTeamPenalties(extractor.extractInt(parseTeamPenalties(item),"awayTeam"));
        match.setMatchTime(parseMatchDay(item));
        match.setLastUpdated(new Date());
        League league = new League();
        league.setLeague_id((long)compId);
        match.setLeague(league);

        Season season = new Season();
        season.setSeason_id((long) parseId(parseSeason(item)));
        match.setSeason(season);

        return match;
    }

    private int parseIdAwayClub(JsonObject item) {
        JsonObject awayTeam = extractor.extractJson(item, "awayTeam");
        return parseId(awayTeam);
    }

    private int parseId(JsonObject json) {
        return extractor.extractInt(json, "id");
    }

    private int parseIdHomeClub(JsonObject json) {
        JsonObject homeTeam = extractor.extractJson(json, "homeTeam");
        return parseId(homeTeam);
    }

    private JsonObject parseScore(JsonObject json) {
        return extractor.extractJson(json, "score");

    }

    private JsonObject parseTeamScore(JsonObject json) {
        JsonObject jsonObject = parseScore(json);
        return extractor.extractJson(jsonObject, "fullTime");
    }

    private JsonObject parseTeamPenalties(JsonObject json) {
        JsonObject jsonObject = parseScore(json);
        return extractor.extractJson(jsonObject, "penalties");
    }
    private Date parseMatchDay(JsonObject json) {
        return Date.from(extractor.extractDateTime(json, "utcDate").atZone(ZoneId.systemDefault()).toInstant());
    }

    private JsonObject parseSeason(JsonObject jsonObject){
        return extractor.extractJson(jsonObject,"season");
    }

    private String parseStatus(JsonObject jsonObject){ return extractor.extractString(jsonObject,"status");}

}
