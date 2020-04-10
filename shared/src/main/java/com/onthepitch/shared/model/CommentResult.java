package com.onthepitch.shared.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CommentResult {
    private Long comment_id;
    private String username;
    private String user_logo;
    private List<ChildComment> comments;
    private String text;
    private Integer likes;
    private Integer dislikes;
    private Boolean isLiked;
    private Boolean isDisliked;

    public CommentResult(Long comment_id, String username,String user_logo, List<ChildComment> comments, String text, Integer likes, Integer dislikes, Boolean isLiked, Boolean isDisliked) {
        this.comment_id = comment_id;
        this.username = username;
        this.user_logo= user_logo;
        this.comments = comments;
        this.text = text;
        this.likes = likes;
        this.dislikes = dislikes;
        this.isLiked = isLiked;
        this.isDisliked = isDisliked;
    }
}
