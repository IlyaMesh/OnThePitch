package com.onthepitch.backend.converter;

import com.onthepitch.backend.commands.PostForm;
import com.onthepitch.backend.model.Post;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

public class PostFormToPost implements Converter<PostForm, Post> {
    @Override
    public Post convert(PostForm postForm) {
        Post post = new Post();
        if (postForm.getPost_id() != null && !StringUtils.isEmpty(postForm.getPost_id())) {
            post.setPost_id(new Long(postForm.getPost_id()));
        }
        post.setUser_id(postForm.getUser_id());
        post.setHeader(postForm.getHeader());
        post.setText(postForm.getText());
        post.setCreated_at(postForm.getCreated_at());
        return post;
    }
}
