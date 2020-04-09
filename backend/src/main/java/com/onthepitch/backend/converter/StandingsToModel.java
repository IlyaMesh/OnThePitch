package com.onthepitch.backend.converter;

import com.onthepitch.backend.commands.StandingsTeam;
import com.onthepitch.shared.model.StandingModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StandingsToModel implements Converter<StandingsTeam, StandingModel> {

    @Override
    public StandingModel convert(StandingsTeam standingsTeam) {
        return new StandingModel(
                Math.toIntExact(standingsTeam.getClub().getClub_id()),
                standingsTeam.getClub().getClub_name(),
                standingsTeam.getClub().getLogo(),
                standingsTeam.getMatchesPlayed(),
                standingsTeam.getWins(),
                standingsTeam.getDraws(),
                standingsTeam.getLoses(),
                standingsTeam.getPoints(),
                standingsTeam.getGoalsScored(),
                standingsTeam.getGoalsAgainst()
        );
    }
}
