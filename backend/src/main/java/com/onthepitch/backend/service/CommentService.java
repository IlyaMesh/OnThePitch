package com.onthepitch.backend.service;

import com.onthepitch.backend.model.Comment;
import com.onthepitch.shared.model.response.CommentResult;

import java.util.List;

public interface CommentService {
    List<Comment> getAllCommentsForPost(long id);
    void saveOrUpdate(Comment comment);

    Comment getById(Long id);

    void delete(Long id);

    void addComment(long parseLong, CommentResult commentResult);
}
