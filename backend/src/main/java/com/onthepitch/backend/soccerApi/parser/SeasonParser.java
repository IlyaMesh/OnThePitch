package com.onthepitch.backend.soccerApi.parser;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.onthepitch.backend.model.Season;
import com.onthepitch.backend.soccerApi.JsonExtractor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SeasonParser {
    private final JsonParser parser = new JsonParser();
    private final JsonExtractor extractor = new JsonExtractor();

//    public Competition toCompetition(String jsonString) {
//        JsonElement element = parser.parse(jsonString);
//        JsonObject json = element.getAsJsonObject();
//        return toCompetition(json);
//    }

//    public Competition toCompetition(JsonObject json) {
//        return Competition.builder()
//                .id(parseId(json))
//                .area(parseArea(json))
//                .name(parseName(json))
//                .plan(parsePlan(json))
//                .code(parseCode(json))
//                .currentSeason(parseSeason(extractor.extractJson(json, "currentSeason")))
//                .numberOfAvailableSeasons(parseNumberOfAvailableSeasons(json))
//                .seasons(parseSeasons(json))
//                .lastUpdated(parseLastUpdated(json))
//                .build();
//    }
    public List<Season> getSeasons(String jsonString){
        JsonElement element = parser.parse(jsonString);
        JsonObject json = element.getAsJsonObject();
        List<Season> seasons = parseSeasons(json).stream()
                .filter(season -> season.getStart_date().after(Date.from(LocalDate.now().minusYears(2).atStartOfDay(ZoneId.systemDefault()).toInstant())))
                .collect(Collectors.toList());
        return seasons;
    }
    private int parseId(JsonObject json) {
        return extractor.extractInt(json, "id");
    }

    private Season parseSeason(JsonObject json) {
        Season season = new Season();
        season.setSeason_id((long) parseId(json));
        season.setStart_date(parseStartDate(json));
        season.setEnd_date(parseEndDate(json));
        season.setCurrentMatchDay(parseCurrentMatchDay(json));
        return season;
    }

    private List<Season> parseSeasons(JsonObject json) {
        List<Season> seasons = new ArrayList<>();
        Integer id = extractor.extractInt(json, "id");
        List<JsonObject> seasonsJson = extractor.extractList(json, "seasons");
        seasonsJson.forEach(item -> seasons.add(parseSeason(item)));
        return seasons;
    }


    private int parseNumberOfAvailableSeasons(JsonObject json) {
        return extractor.extractInt(json, "numberOfAvailableSeasons");
    }

    private String parseName(JsonObject json) {
        return extractor.extractString(json, "name");
    }

    private String parsePlan(JsonObject json) {
        return extractor.extractString(json, "plan");
    }

    private String parseCode(JsonObject json) {
        return extractor.extractString(json, "code");
    }

    private Date parseStartDate(JsonObject json) {
        return Date.from(extractor.extractLocalDate(json, "startDate").atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private Date parseEndDate(JsonObject json) {
        return Date.from(extractor.extractLocalDate(json, "endDate").atStartOfDay(ZoneId.systemDefault()).toInstant());
    }


    private int parseCurrentMatchDay(JsonObject json) {
        return extractor.extractInt(json, "currentMatchday");
    }

    private LocalDateTime parseLastUpdated(JsonObject json) {
        return extractor.extractDateTime(json, "lastUpdated");
    }

}
