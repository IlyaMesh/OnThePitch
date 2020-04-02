package com.onthepitch.backend.service;

import com.onthepitch.backend.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComments();

    List<Comment> getAllCommentsForPost(long id);
    void saveOrUpdate(Comment comment);

    Comment getById(Long id);

    void delete(Long id);
}
