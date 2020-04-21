package com.onthepitch.backend.repos;

import com.onthepitch.backend.model.Post;
import com.onthepitch.backend.repos.query.QueryUtils;
import com.onthepitch.shared.model.response.PostResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface PostRepository extends CrudRepository<Post, Long> {

    Page<Post> findAll(Pageable pageable);

    @Query( value = QueryUtils.FIND_POSTS_WITH_RATING,
            countQuery = QueryUtils.PAGEABLE_COUNT_FOR_POSTS,
            nativeQuery = true)
    Page<Post> findPostsWithRating(Pageable pageable);

    @Query(value = "select p from Post p where p.header like %:text% or p.text like %:text%")
    Page<Post> findPostLike(@Param("text") String text, Pageable pageable);
}
