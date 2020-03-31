package com.onthepitch.backend.repos;

import com.onthepitch.backend.model.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends CrudRepository<Comment,Long> {

    @Query(value = "select c from Comment c where c.replyTo is null and c.post.post_id = :id")
    Iterable<Comment> findRootComments(@Param("id") long id);

    @Query(value = "select c from Comment c where c.post.post_id = :id order by c.replyTo.comment_id nulls last")
    Iterable<Comment> findAllCommentsOfPost(@Param("id") long id);

    @Query("select uc from Comment uc where uc.replyTo is not null and uc.post.post_id =:id")
    Iterable<Comment> getSecondLevelComments(@Param("id") long id);
}
