package com.onthepitch.backend.service;

import com.onthepitch.shared.model.response.ClubForm;
import com.onthepitch.backend.model.Club;

import java.util.List;

public interface ClubService {
    List<Club> listAll();

    Club getById(Long id);

    Club saveOrUpdate(Club club);

    void delete(Long id);

    List<Club> getFromApi(Long id);

    void updateClubForUser(long parseLong);

}
