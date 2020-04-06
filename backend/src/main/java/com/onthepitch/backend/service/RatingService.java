package com.onthepitch.backend.service;

import com.onthepitch.backend.model.Rating;
import com.onthepitch.backend.model.User;

import java.util.List;

public interface RatingService {

    List<Rating> findAll();

    Rating getByNoteId( Long id);

    Rating save(Rating r);

    void createRating(String authorname, Long note_id,boolean isLiked);

    void delete(User user, Long note_id);

    void removerating(String userName, long parseLong);
}
