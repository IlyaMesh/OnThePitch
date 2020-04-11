package com.onthepitch.backend.converter;

import com.onthepitch.backend.model.Comment;
import com.onthepitch.backend.model.Rating;
import com.onthepitch.backend.model.User;
import com.onthepitch.backend.repos.RatingRepository;
import com.onthepitch.backend.repos.UserRepo;
import com.onthepitch.shared.model.ChildComment;
import com.onthepitch.shared.model.CommentResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CommentToCommentResult implements Converter<Comment, CommentResult> {
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private UserRepo userRepo;

    //TODO make order here
    private Integer getLikes(List<Rating> ratings) {
        Long count = ratings.stream().filter(rating -> rating.isLike()).count();
        return count.intValue();
    }

    private Integer getDislikes(List<Rating> ratings) {
        Long count = ratings.stream().filter(rating -> !rating.isLike()).count();
        return count.intValue();
    }

    private Boolean isUserLiked(Comment comment, User user) {
        Rating rateByUserAndNote = ratingRepository.findRateByUserAndNote(user, comment.getComment_id()).orElse(null);
        if (rateByUserAndNote == null) {
            return null;
        }
        return rateByUserAndNote.isLike();
    }

    @Override
    public CommentResult convert(Comment comment) {
        List<Rating> ratings = ratingRepository.getAllByNote_id(comment.getComment_id());
//        String UserName = SecurityContextHolder.getContext().getAuthentication().getName();
//        User currentUser = userRepo.findByUsername(UserName);
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Boolean userLiked = isUserLiked(comment, currentUser);
        Boolean isLiked;
        Boolean isDisliked;
        if (userLiked == null) {
            isLiked = false;
            isDisliked = false;
        }
        else {
            isLiked = userLiked;
            isDisliked = !userLiked;
        }
        Integer likes = getLikes(ratings);
        Integer dislikes = getDislikes(ratings);
        return new CommentResult(
                comment.getComment_id(),
                comment.getAuthor().getUsername(),
                comment.getAuthor().getUser_pic(),
                new ArrayList<>(),
                comment.getText(),
                likes,
                dislikes,
                isLiked,
                isDisliked
        );
    }

    public List<CommentResult> convertList(List<Comment> list) {
        Queue<Comment> comments = new LinkedList<>();
        List<CommentResult> result = new ArrayList<>();
        comments.addAll(list);
        while (!comments.isEmpty()) {
            Comment peek = comments.peek();
            if (peek.getReplyTo() == null) {
                comments.remove();
                result.add(convert(peek));
                continue;
            }
            if (IfInComments(result, peek)) {
                comments.remove();
            }
            if (IfInCommentsChild(result, peek)) {
                comments.remove();
            }
            if (comments.isEmpty()) {
                continue;
            }
            Comment remove = comments.remove();
            comments.add(remove);
        }
        return result;
    }

    boolean IfInComments(List<CommentResult> rootCom, Comment comment) {
        for (CommentResult commentResult : rootCom) {
            if (comment.getReplyTo().getComment_id() == commentResult.getComment_id()) {
                commentResult.getComments().add(convertCommentToChild(comment));
                return true;
            }
        }
        return false;
    }

    boolean IfInCommentsChild(List<CommentResult> rootCom, Comment comment) {
        for (CommentResult commentResult : rootCom) {
            for (ChildComment childComment : commentResult.getComments()) {
                if (comment.getReplyTo().getComment_id() == childComment.getComment_id()) {
                    commentResult.getComments().add(convertCommentToChild(comment));
                    return true;
                }
            }
        }
        return false;
    }

    private ChildComment convertCommentToChild(Comment comment) {
        List<Rating> ratings = ratingRepository.getAllByNote_id(comment.getComment_id());
        Integer likes = getLikes(ratings);
        Integer dislikes = getDislikes(ratings);
//        String UserName = SecurityContextHolder.getContext().getAuthentication().getName();
//        User currentUser = userRepo.findByUsername(UserName);
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Boolean userLiked = isUserLiked(comment, currentUser);
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

        return new ChildComment(
                comment.getComment_id(),
                comment.getAuthor().getUsername(),
                comment.getAuthor().getUser_pic(),
                comment.getText(),
                likes,
                dislikes,
                comment.getReplyTo().getComment_id().toString(),
                comment.getReplyTo().getAuthor().getUsername(),
                isLiked,
                isDisliked);
    }


}
