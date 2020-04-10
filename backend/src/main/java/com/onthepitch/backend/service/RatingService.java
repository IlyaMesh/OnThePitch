package com.onthepitch.backend.service;

import com.onthepitch.backend.model.Rating;
import com.onthepitch.backend.model.User;

import java.util.List;

public interface RatingService {

    List<Rating> findAll();

    Rating getByNoteId( Long id);

    Rating save(Rating r);

    void createRating(Long note_id,boolean isLiked);

    void removeRating(long parseLong);
}
