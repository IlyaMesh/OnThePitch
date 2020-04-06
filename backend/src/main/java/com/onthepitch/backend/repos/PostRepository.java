package com.onthepitch.backend.repos;

import com.onthepitch.backend.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


public interface PostRepository extends CrudRepository<Post, Long> {
    Page<Post> findAll(Pageable pageable);
}
