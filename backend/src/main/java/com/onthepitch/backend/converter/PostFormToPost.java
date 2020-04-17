package com.onthepitch.backend.converter;

import com.onthepitch.backend.model.Post;
import com.onthepitch.shared.model.response.PostResult;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

@Component
public class PostFormToPost implements Converter<PostResult, Post> {
    @Override
    public Post convert(PostResult postResult) {
        Post post = new Post();
        if (postResult.getPost_id() != null && !StringUtils.isEmpty(postResult.getPost_id())) {
            post.setPost_id(postResult.getPost_id());
        }
        post.setHeader(postResult.getHeader());
        post.setText(postResult.getText());
        post.setCreated_at(new Date());
        return post;
    }
}
