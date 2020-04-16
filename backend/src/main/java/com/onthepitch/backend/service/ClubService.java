package com.onthepitch.backend.service;

import com.onthepitch.shared.model.ClubForm;
import com.onthepitch.backend.model.Club;

import java.util.List;

public interface ClubService {
    List<Club> listAll();

    Club getById(Long id);

    Club saveOrUpdate(Club club);

    void delete(Long id);

    Club saveOrUpdatePostForm(ClubForm clubForm);

    List<Club> getFromApi(Long id);

    void updateClubForUser(long parseLong);

}
