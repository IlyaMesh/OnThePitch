package com.onthepitch.backend.service;

import com.onthepitch.backend.commands.PostForm;
import com.onthepitch.backend.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    public List<Post> listAll();

    Post getById(Long id);

    Post saveOrUpdate(Post post);

    void delete(Long id);

    Post saveOrUpdatePostForm(PostForm productForm);
}
