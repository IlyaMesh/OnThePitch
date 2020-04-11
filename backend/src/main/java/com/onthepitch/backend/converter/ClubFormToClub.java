package com.onthepitch.backend.converter;

import com.onthepitch.backend.commands.ClubForm;
import com.onthepitch.backend.model.Club;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ClubFormToClub implements Converter<ClubForm,Club> {
    @Override
    public Club convert(ClubForm clubForm) {
        Club club = new Club();
        if (clubForm.getClub_id() != null && !StringUtils.isEmpty(clubForm.getClub_id())) {
            club.setClub_id(clubForm.getClub_id());
        }
        club.setClub_name(clubForm.getClub_name());
        club.setLogo(clubForm.getLogo());
        club.setStadium(clubForm.getStadium());
        club.setClub_info(clubForm.getClub_info());
        return club;
    }


}
