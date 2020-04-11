package com.onthepitch.backend.service.serviceImpl;

import com.onthepitch.backend.model.Rating;
import com.onthepitch.backend.model.User;
import com.onthepitch.backend.repos.RatingRepository;
import com.onthepitch.backend.repos.UserRepo;
import com.onthepitch.backend.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating save(Rating r) {
        ratingRepository.save(r);
        return r;
    }

    @Override
    public void createRating(Long note_id, boolean isLiked) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Rating rating1 = ratingRepository.findRateByUserAndNote(user, note_id).orElse(null);
        if(rating1!=null){
            removeRating(note_id);
            if(rating1.isLike()==isLiked){
                return;
            }
        }
        Rating rating = new Rating();
        rating.setUser(user);
        rating.setNote_id(note_id);
        rating.setRated_at(new Date());
        rating.setLike(isLiked);
        ratingRepository.save(rating);
    }

    @Override
    public void removeRating(long note_id) {
        //User user = userRepo.findByUsername(userName);
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ratingRepository.deleteByUserAndNote_id(user,note_id);
    }
}
