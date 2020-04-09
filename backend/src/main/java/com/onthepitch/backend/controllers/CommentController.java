package com.onthepitch.backend.controllers;

import com.onthepitch.backend.converter.CommentToCommentResult;
import com.onthepitch.backend.model.Comment;
import com.onthepitch.backend.model.User;
import com.onthepitch.backend.repos.UserRepo;
import com.onthepitch.backend.service.CommentService;
import com.onthepitch.backend.service.PostService;
import com.onthepitch.shared.model.ChildComment;
import com.onthepitch.shared.model.CommentResult;
import com.onthepitch.shared.model.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@CrossOrigin(origins = "*")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentToCommentResult commentToCommentResult;
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/comments/{id}")
    public List<CommentResult> getComments(@PathVariable(name = "id") String id) {

        List<Comment> allComments = commentService.getAllCommentsForPost(Long.parseLong(id));
        List<CommentResult> commentResults = commentToCommentResult.convertList(allComments);

        return commentResults;
    }

    @PostMapping("/comments/{id}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('MODERATOR') or hasAuthority('ADMIN')")
    public void addComment(@PathVariable(name = "id") String id, @RequestBody ChildComment commentResult) {
        String UserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByUsername(UserName);
        Comment newComment = new Comment();
        newComment.setAuthor(user);
        newComment.setText(commentResult.getText());
        newComment.setPost(postService.getById(Long.parseLong(id)));
        if (commentResult.getReply_id().matches("[-+]?\\d+")) {
            newComment.setReplyTo(commentService.getById(Long.parseLong(commentResult.getReply_id())));
        } else {
            newComment.setReplyTo(null);
        }
        commentService.saveOrUpdate(newComment);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        commentService.delete(Long.valueOf(id));
        return ResponseEntity.ok(new MessageResponse("Comment deleted"));
    }
}
