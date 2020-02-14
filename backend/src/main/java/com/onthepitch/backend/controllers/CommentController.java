package com.onthepitch.backend.controllers;

import com.onthepitch.backend.commands.CommentForm;
import com.onthepitch.backend.model.Comment;
import com.onthepitch.backend.model.Post;
import com.onthepitch.backend.model.User;
import com.onthepitch.backend.service.CommentService;
import com.onthepitch.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private PostService postService;
    @RequestMapping("/comment/add/{id}")
    public String newComment(Model model,@PathVariable String id){
        CommentForm commentForm = new CommentForm();
        commentForm.setNote_id(Long.valueOf(id));
        model.addAttribute("commentForm",commentForm);
//        Comment comment = new Comment();
//        comment.setAuthor(user);
//        if(postService.getById(idNote)!=null){
//        comment.setPost(postService.getById(idNote));
//        }
//        //если это комментарий на комментарий
//        if(commentService.getById(idNote)!=null){
//            Comment rootComment = commentService.getById(idNote);
//            comment.setReplyTo(rootComment);
//        }
//        comment.setText("Wow that's auto-generated comment");
//        commentService.SaveOrUpdate(comment);
        return "comment/add";
    }
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public String saveComment(@AuthenticationPrincipal User user,@Valid CommentForm commentForm){
        Comment comment = new Comment();
        Long idNote = commentForm.getNote_id();
        comment.setAuthor(user);
        if(postService.getById(idNote)!=null){
        comment.setPost(postService.getById(idNote));
        }
        //если это комментарий на комментарий
        if(commentService.getById(idNote)!=null){
            Comment rootComment = commentService.getById(idNote);
            comment.setReplyTo(rootComment);
        }
        comment.setText(commentForm.getText());
        commentService.SaveOrUpdate(comment);
        return "post/list";
    }

    @RequestMapping("/comment/delete/{id}")
    public String delete(@PathVariable String id) {
        commentService.delete(Long.valueOf(id));
        return "redirect:/post/list";
    }
}
