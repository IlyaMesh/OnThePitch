package com.onthepitch.shared.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostResult {
    private Long post_id;
    private String username;
    private String header;
    private String text;
    private String created_at;
    private Integer comments_count;
    private Integer likes;
    private Integer dislikes;
    private Boolean isLiked;
    private Boolean isDisliked;

    public PostResult(
            Long post_id,
            String username,
            String header,
            String text,
            String created_at,
            Integer comments_count,
            Integer likes,
            Integer dislikes,
            Boolean isLiked,
            Boolean isDisliked) {
        this.post_id = post_id;
        this.username = username;
        this.header = header;
        this.text = text;
        this.created_at = created_at;
        this.comments_count = comments_count;
        this.likes = likes;
        this.dislikes = dislikes;
        this.isLiked = isLiked;
        this.isDisliked = isDisliked;
    }
}
