package com.onthepitch.backend.dao;

import com.onthepitch.backend.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment,Long> {
}
