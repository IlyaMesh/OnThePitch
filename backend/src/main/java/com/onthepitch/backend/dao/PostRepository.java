package com.onthepitch.backend.dao;

import com.onthepitch.backend.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post,Long> {

}
