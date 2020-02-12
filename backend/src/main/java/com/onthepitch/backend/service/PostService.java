package com.onthepitch.backend.service;

import com.onthepitch.backend.commands.PostForm;
import com.onthepitch.backend.model.Post;
import com.onthepitch.backend.model.User;

import java.util.List;

public interface PostService {
    public List<Post> listAll();

    Post getById(Long id);

    Post saveOrUpdate(Post post);

    void delete(Long id);

    Post saveOrUpdatePostForm(PostForm productForm, User user);
}
