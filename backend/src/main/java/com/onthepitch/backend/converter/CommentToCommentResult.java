package com.onthepitch.backend.converter;

import com.onthepitch.backend.model.Comment;
import com.onthepitch.backend.model.Post;
import com.onthepitch.shared.model.CommentResult;
import com.onthepitch.shared.model.ChildComment;
import com.onthepitch.shared.model.PostResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CommentToCommentResult implements Converter<Comment, CommentResult> {

    @Override
    public CommentResult convert(Comment comment) {
        CommentResult commentResult = new CommentResult(
                comment.getComment_id(),
                comment.getAuthor().getUsername(),
                new ArrayList<ChildComment>(),
                comment.getText(),
                comment.getLikes(),
                comment.getDislikes(),
                comment.getReports()
        );

        return commentResult;
    }

    public List<CommentResult> convertList(List<Comment> list) {
        Queue<Comment> comments = new LinkedList<>();
        List<CommentResult> result = new ArrayList<>();
        for (Comment comment : list) {
            comments.add(comment);
        }
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
        return new ChildComment(
                comment.getComment_id(),
                comment.getAuthor().getUsername(),
                comment.getText(),
                comment.getLikes(),
                comment.getDislikes(),
                comment.getReports(),
                comment.getReplyTo().getComment_id().toString(),
                comment.getReplyTo().getAuthor().getUsername());
    }


}
