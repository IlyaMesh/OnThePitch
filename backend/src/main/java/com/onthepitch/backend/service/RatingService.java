package com.onthepitch.backend.service;

import com.onthepitch.backend.model.Rating;

import java.util.List;

public interface RatingService {

    List<Rating> findAll();

    Rating save(Rating r);

    void createRating(Long note_id,boolean isLiked);

    void removeRating(long parseLong);
}
