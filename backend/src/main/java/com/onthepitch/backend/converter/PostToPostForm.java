package com.onthepitch.backend.converter;


import com.onthepitch.backend.commands.PostForm;
import com.onthepitch.backend.model.Post;
import com.onthepitch.shared.model.PostResult;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PostToPostForm implements Converter<Post, PostResult> {
    @Override
    public PostResult convert(Post post) {
        PostResult postResult = new PostResult(
                post.getPost_id(),
                post.getAuthorName(),
                post.getHeader(),
                post.getText(),
                post.getCreated_at(),
                post.getComments().size()
        );
        return postResult;
    }
}
