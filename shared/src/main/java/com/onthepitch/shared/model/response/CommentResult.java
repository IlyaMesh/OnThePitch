package com.onthepitch.shared.model.response;

import com.onthepitch.shared.model.response.ChildComment;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CommentResult {
    private Long comment_id;
    private String username;
    private String user_logo;
    private List<CommentResult> comments;
    private String text;
    private Integer likes;
    private Integer dislikes;
    private String reply_id;
    private String reply_name;
    private Boolean isLiked;
    private Boolean isDisliked;

    public CommentResult(Long comment_id, String username,String user_logo, List<CommentResult> comments, String text, Integer likes, Integer dislikes,String reply_id,String reply_name, Boolean isLiked, Boolean isDisliked) {
        this.comment_id = comment_id;
        this.username = username;
        this.user_logo= user_logo;
        this.comments = comments;
        this.text = text;
        this.likes = likes;
        this.dislikes = dislikes;
        this.reply_id = reply_id;
        this.reply_name = reply_name;
        this.isLiked = isLiked;
        this.isDisliked = isDisliked;
    }
}
