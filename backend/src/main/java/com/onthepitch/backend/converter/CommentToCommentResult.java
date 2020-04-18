package com.onthepitch.backend.converter;

import com.onthepitch.backend.model.Comment;
import com.onthepitch.backend.model.Rating;
import com.onthepitch.backend.model.User;
import com.onthepitch.backend.repos.RatingRepository;
import com.onthepitch.shared.model.response.CommentResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Component
public class CommentToCommentResult implements Converter<Comment, CommentResult> {
    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public CommentResult convert(Comment comment) {
        List<Rating> ratings = ratingRepository.getAllByNote_id(comment.getComment_id());
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = principal instanceof UserDetails ? (User)principal : null;
        Boolean isRoot= comment.getReplyTo() == null;
        Boolean userLiked = isUserLiked(comment, currentUser);
        Boolean isLiked = userLiked == null ? false : userLiked;
        Boolean isDisliked = userLiked == null ? false : !userLiked;
        return new CommentResult(
                comment.getComment_id(),
                comment.getAuthor().getUsername(),
                comment.getAuthor().getUser_pic(),
                isRoot ? new ArrayList<>() : null,
                comment.getText(),
                getLikes(ratings),
                getDislikes(ratings),
                isRoot? null : comment.getReplyTo().getComment_id().toString(),
                isRoot? null : comment.getReplyTo().getAuthor().getUsername(),
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
            if (comments.isEmpty()) {
                continue;
            }
            Comment remove = comments.remove();
            comments.add(remove);
        }
        return result;
    }

    boolean IfInComments(List<CommentResult> rootCom, Comment comment) {
        //не использую рекурсию, т.к. два уровня вложенности
        for (CommentResult commentResult : rootCom) {
            if (comment.getReplyTo().getComment_id() == commentResult.getComment_id()) {
                commentResult.getComments().add(convert(comment));
                return true;
            }
            if(commentResult.getComments()!=null){
                for (CommentResult commentResult1 : commentResult.getComments()) {
                    if (comment.getReplyTo().getComment_id() == commentResult1.getComment_id()) {
                        commentResult.getComments().add(convert(comment));
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private Integer getLikes(List<Rating> ratings) {
        Long count = ratings.stream().filter(rating -> rating.isLike()).count();
        return count.intValue();
    }

    private Integer getDislikes(List<Rating> ratings) {
        Long count = ratings.stream().filter(rating -> !rating.isLike()).count();
        return count.intValue();
    }

    private Boolean isUserLiked(Comment comment, User user) {
        Rating rateByUserAndNote = ratingRepository.findRateByUserAndNote(user, comment.getComment_id())
                                                    .orElse(null);
        return rateByUserAndNote == null ? null : rateByUserAndNote.isLike();
    }

}
