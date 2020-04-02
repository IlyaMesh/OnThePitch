package com.onthepitch.backend.converter;

import com.onthepitch.backend.model.Comment;
import com.onthepitch.shared.model.CommentResult;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommentResultToComment implements Converter<CommentResult, Comment> {
    @Override
    public Comment convert(CommentResult commentResult) {
       Comment comment = new Comment();
       comment.setText(commentResult.getText());
       return comment;
    }
}
