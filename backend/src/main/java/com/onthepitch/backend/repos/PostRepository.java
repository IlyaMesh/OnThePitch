package com.onthepitch.backend.repos;

import com.onthepitch.backend.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface PostRepository extends CrudRepository<Post, Long> {
    Page<Post> findAll(Pageable pageable);

    @Query(value = "select * from POST" +
            "    left join (\n" +
            "        select rat.note_id, count(rat.is_like) as likes \n" +
            "        from RATING rat\n" +
            "        where rat.is_like = 1\n" +
            "        group by rat.note_id\n" +
            "    ) r on\n" +
            "    r.note_id = post_id\n" +
            "order by r.likes desc", nativeQuery = true)
    Page<Post> findPostsByRating(Pageable pageable);

    @Query(value = "select p from Post p where p.header like %:text% or p.text like %:text%")
    Page<Post> findPostLike(@Param("text") String text, Pageable pageable);
}
