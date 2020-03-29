package com.onthepitch.backend.converter;

import com.onthepitch.backend.model.Comment;
import com.onthepitch.backend.model.Post;
import com.onthepitch.shared.model.CommentResult;
import com.onthepitch.shared.model.PostResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentToCommentResult implements Converter<Comment, CommentResult> {

    @Override
    public CommentResult convert(Comment comment) {
        if(comment == null){
            return null;
        }
        CommentResult commentResult = new CommentResult(
                comment.getComment_id(),
                comment.getAuthor().getUsername(),
                convertList(comment.getComments()),
                comment.getText(),
                comment.getLikes(),
                comment.getDislikes(),
                comment.getReports()
        );
        return commentResult;
    }
    public List<CommentResult> convertList(List<Comment> list){
        List<CommentResult> result = new ArrayList<>();
        for (Comment comment : list){
            result.add(convert(comment));
        }
        return result;
    }
}
