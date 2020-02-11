package com.onthepitch.backend.controllers;

import com.onthepitch.backend.model.Comment;
import com.onthepitch.backend.model.User;
import com.onthepitch.backend.service.CommentService;
import com.onthepitch.backend.service.PostService;
import org.hibernate.hql.internal.ast.ParseErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private PostService postService;
    @RequestMapping("/comment/add/{id}")
    public String newComment(@AuthenticationPrincipal User user, @PathVariable String id){
        //TODO move this part to better place
        Comment comment = new Comment();
        comment.setAuthor(user);
        comment.setPost(postService.getById(Long.valueOf(id)));
        comment.setText("Wow that's auto-generated comment");
        commentService.SaveOrUpdate(comment);
        return "post/list";
    }
}
