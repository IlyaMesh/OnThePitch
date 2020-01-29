package com.onthepitch.backend.converter;

import com.onthepitch.backend.commands.ClubForm;
import com.onthepitch.backend.model.Club;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClubToClubForm implements Converter<Club, ClubForm> {
    @Override
    public ClubForm convert(Club club) {
        ClubForm clubForm = new ClubForm();
        clubForm.setClub_id(club.getClub_id());
        clubForm.setClub_name(club.getClub_name());
        clubForm.setLogo(club.getLogo());
        clubForm.setStadium(club.getStadium());
        clubForm.setClub_info(club.getClub_info());
        return clubForm;
    }
}
