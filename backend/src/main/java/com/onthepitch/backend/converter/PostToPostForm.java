package com.onthepitch.backend.converter;


import com.onthepitch.backend.commands.PostForm;
import com.onthepitch.backend.model.Post;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PostToPostForm implements Converter<Post, PostForm> {
    @Override
    public PostForm convert(Post post) {
        PostForm postForm = new PostForm();
        postForm.setPost_id(post.getPost_id());
        postForm.setUser(post.getAuthor());
        postForm.setHeader(post.getHeader());
        postForm.setText(post.getText());
        postForm.setCreated_at(post.getCreated_at());
        return postForm;
    }
}