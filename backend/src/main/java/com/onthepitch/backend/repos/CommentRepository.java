package com.onthepitch.backend.repos;

import com.onthepitch.backend.model.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment,Long> {
    @Override
    @Query(value = "select c from Comment c where c.replyTo is null")
    Iterable<Comment> findAll();
}
