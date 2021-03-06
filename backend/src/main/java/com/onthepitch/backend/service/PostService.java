package com.onthepitch.backend.service;

import com.onthepitch.backend.model.Post;
import com.onthepitch.shared.model.response.PostResult;
import org.springframework.data.domain.Page;

public interface PostService {
    Page<PostResult> listAll(int page, int size);

    Page<PostResult> listAll(int page, int size, String text);

    PostResult getById(Long id);

    Post saveOrUpdate(Post post);

    void delete(Long id);

    void addPost(PostResult post);

    Post getPostById(Long parseLong);
}
