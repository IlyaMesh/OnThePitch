package com.onthepitch.backend.service;

import com.onthepitch.backend.model.Comment;

public interface CommentService {
    void SaveOrUpdate(Comment comment);

    Comment getById(Long id);

    void delete(Long id);
}
