package com.onthepitch.backend.service;

import com.onthepitch.backend.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComments();

    void SaveOrUpdate(Comment comment);

    Comment getById(Long id);

    void delete(Long id);
}
