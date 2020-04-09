package com.onthepitch.backend.converter;


import com.onthepitch.backend.commands.PostForm;
import com.onthepitch.backend.model.Comment;
import com.onthepitch.backend.model.Post;
import com.onthepitch.backend.model.Rating;
import com.onthepitch.backend.model.User;
import com.onthepitch.backend.repos.RatingRepository;
import com.onthepitch.backend.repos.UserRepo;
import com.onthepitch.shared.model.PostResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

@Component
public class PostToPostForm implements Converter<Post, PostResult> {
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private UserRepo userRepo;

    private Integer getLikes(List<Rating> ratings) {
        Long count = ratings.stream().filter(rating -> rating.isLike()).count();
        return count.intValue();
    }

    private Integer getDislikes(List<Rating> ratings) {
        Long count = ratings.stream().filter(rating -> !rating.isLike()).count();
        return count.intValue();
    }

    private Boolean isUserLiked(Post post, User user) {
        Rating rateByUserAndNote = ratingRepository.findRateByUserAndNote(user, post.getPost_id()).orElse(null);
        if (rateByUserAndNote == null) {
            return null;
        }
        return rateByUserAndNote.isLike();
    }

    @Override
    public PostResult convert(Post post) {
        List<Rating> ratings = ratingRepository.getAllByNote_id(post.getPost_id());
        String UserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepo.findByUsername(UserName);
        Boolean userLiked = isUserLiked(post, currentUser);
        Boolean isLiked;
        Boolean isDisliked;
        if (userLiked == null) {
            isLiked = false;
            isDisliked = false;
        }
        else{
            isLiked = userLiked;
            isDisliked = !userLiked;
        }
        Integer likes = getLikes(ratings);
        Integer dislikes = getDislikes(ratings);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yy HH:mm", Locale.UK);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
        return new PostResult(
                post.getPost_id(),
                post.getAuthorName(),
                post.getHeader(),
                post.getText(),
                simpleDateFormat.format(post.getCreated_at()),
                post.getComments().size(),
                likes,
                dislikes,
                isLiked,
                isDisliked
        );
    }
}
