package com.onthepitch.backend.service.serviceImpl;

import com.onthepitch.backend.model.Rating;
import com.onthepitch.backend.model.User;
import com.onthepitch.backend.repos.RatingRepository;
import com.onthepitch.backend.repos.UserRepo;
import com.onthepitch.backend.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Rating getByNoteId(Long id) {
        return ratingRepository.getByNoteId(id);
    }

    @Override
    public Rating save(Rating r) {
        ratingRepository.save(r);
        return r;
    }

    @Override
    public void createRating(String authorname, Long note_id, boolean isLiked) {

        //добавить проверку не имеется ли уже лайка/дизлайка, если имеется -> удалить
        User user = userRepo.findByUsername(authorname);
        if(ratingRepository.existsByUserAndAndNote_id(user,note_id)){
            //не работает удаление
            delete(user,note_id);
        }
        Rating rating = new Rating();
        rating.setUser(user);
        rating.setNote_id(note_id);
        rating.setRated_at(new Date());
        rating.setLike(isLiked);
        ratingRepository.save(rating);
    }

    @Override
    public void delete(User user, Long note_id) {
        ratingRepository.deleteByUserAndNote_id(user,note_id);
    }

    @Override
    public void removerating(String userName, long note_id) {
        User user = userRepo.findByUsername(userName);
        delete(user,note_id);
    }
}
