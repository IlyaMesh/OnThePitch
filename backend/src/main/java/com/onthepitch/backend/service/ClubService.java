package com.onthepitch.backend.service;

import com.onthepitch.backend.commands.ClubForm;
import com.onthepitch.backend.model.Club;

import java.util.List;
import java.util.Optional;

public interface ClubService {
    public List<Club> listAll();

    Club getById(Long id);

    Club saveOrUpdate(Club club);

    void delete(Long id);

    Club saveOrUpdatePostForm(ClubForm clubForm);

    List<Club> getFromApi(Long id);
}
