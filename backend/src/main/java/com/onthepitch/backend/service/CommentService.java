package com.onthepitch.backend.service;

import com.onthepitch.backend.model.Comment;

public interface CommentService {
    void SaveOrUpdate(Comment comment);
}
