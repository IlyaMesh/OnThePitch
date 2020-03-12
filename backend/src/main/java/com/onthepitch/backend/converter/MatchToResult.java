package com.onthepitch.backend.converter;

import com.onthepitch.shared.model.MatchesResult;
import org.springframework.core.convert.converter.Converter;
import com.onthepitch.backend.model.Match;
import org.springframework.stereotype.Component;

@Component
public class MatchToResult implements Converter<Match,MatchesResult> {
    @Override
    public MatchesResult convert(Match match) {
        MatchesResult matchesResult = new MatchesResult(
                match.getMatch_id(),
                match.getHomeTeam().getClub_id(),
                match.getHomeTeam().getClub_name(),
                match.getAwayTeam().getClub_id(),
                match.getAwayTeam().getClub_name(),
                match.getHomeTeamScored(),
                match.getAwayTeamScored(),
                match.getHomeTeamPenalties(),
                match.getAwayTeamPenalties(),
                match.getMatchTime(),
                match.getLeague().getLeague_id(),
                match.getLeague().getLeague_title()
        );
        return matchesResult;
    }
}
