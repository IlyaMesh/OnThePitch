package com.onthepitch.backend.converter;

import com.onthepitch.shared.model.response.MatchesResult;
import org.springframework.core.convert.converter.Converter;
import com.onthepitch.backend.model.Match;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

@Component
public class MatchToResult implements Converter<Match,MatchesResult> {
    @Override
    public MatchesResult convert(Match match) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yy HH:mm", Locale.UK);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
        return new MatchesResult(
                match.getMatch_id(),
                match.getHomeTeam().getClub_id(),
                match.getHomeTeam().getClub_name(),
                match.getHomeTeam().getLogo(),
                match.getAwayTeam().getClub_id(),
                match.getAwayTeam().getClub_name(),
                match.getAwayTeam().getLogo(),
                match.getHomeTeamScored(),
                match.getAwayTeamScored(),
                match.getHomeTeamPenalties(),
                match.getAwayTeamPenalties(),
                simpleDateFormat.format(match.getMatchTime()),
                match.getLeague().getLeague_id(),
                match.getLeague().getLeague_title()
        );
    }
}
