package com.onthepitch.backend.repos;

import com.onthepitch.backend.model.Comment;
import com.onthepitch.backend.repos.query.QueryUtils;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends CrudRepository<Comment,Long> {

    @Query(value = QueryUtils.FIND_ROOT_COMMENTS)
    Iterable<Comment> findRootComments(@Param("id") long id);

    @Query(QueryUtils.FIND_ALL_COMMENTS_OF_POST)
    Iterable<Comment> findAllCommentsOfPost(@Param("id") long id);

}
