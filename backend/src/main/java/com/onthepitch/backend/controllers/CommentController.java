package com.onthepitch.backend.controllers;

import com.onthepitch.backend.converter.CommentToCommentResult;
import com.onthepitch.backend.model.Comment;
import com.onthepitch.backend.model.User;
import com.onthepitch.backend.service.CommentService;
import com.onthepitch.backend.service.LogService;
import com.onthepitch.backend.service.PostService;
import com.onthepitch.shared.model.response.CommentResult;
import com.onthepitch.shared.model.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
public class CommentController {

    private CommentService commentService;
    private CommentToCommentResult commentToCommentResult;
    private LogService logService;

    @Autowired
    public CommentController(CommentService commentService,
                             CommentToCommentResult commentToCommentResult,
                             LogService logService) {
        this.commentService = commentService;
        this.commentToCommentResult = commentToCommentResult;
        this.logService = logService;
    }

    @GetMapping("/comments/{id}")
    public List<CommentResult> getComments(@PathVariable(name = "id") String id) {

        List<Comment> allComments = commentService.getAllCommentsForPost(Long.parseLong(id));
        return commentToCommentResult.convertList(allComments);
    }

    @PostMapping("/comments/{id}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('MODERATOR') or hasAuthority('ADMIN')")
    public void addComment(@PathVariable(name = "id") String id, @RequestBody CommentResult commentResult) {

        commentService.addComment(Long.parseLong(id), commentResult);

    }

    @DeleteMapping("/comments/{id}")
    @PreAuthorize("hasAuthority('MODERATOR') or hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable String id) {
        commentService.delete(Long.valueOf(id));
        logService.addLog("Delete comment");
        return ResponseEntity.ok(new MessageResponse("Comment deleted"));
    }
}
