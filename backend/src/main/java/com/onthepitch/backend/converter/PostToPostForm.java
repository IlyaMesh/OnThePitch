package com.onthepitch.backend.converter;


import com.onthepitch.backend.commands.PostForm;
import com.onthepitch.backend.model.Post;
import com.onthepitch.shared.model.PostResult;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

@Component
public class PostToPostForm implements Converter<Post, PostResult> {
    @Override
    public PostResult convert(Post post) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yy HH:mm", Locale.UK);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
        PostResult postResult = new PostResult(
                post.getPost_id(),
                post.getAuthorName(),
                post.getHeader(),
                post.getText(),
                simpleDateFormat.format(post.getCreated_at()),
                post.getComments().size()
        );
        return postResult;
    }
}
