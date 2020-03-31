package com.onthepitch.backend.controllers;

import com.onthepitch.backend.converter.CommentToCommentResult;
import com.onthepitch.backend.model.Comment;
import com.onthepitch.backend.service.CommentService;
import com.onthepitch.shared.model.CommentResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@CrossOrigin(origins = "*")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentToCommentResult commentToCommentResult;

    @GetMapping("/comments/{id}")
    public List<CommentResult> getComments(@PathVariable(name = "id") String id) {

        List<Comment> allComments = commentService.getAllCommentsForPost(Long.parseLong(id));
        List<CommentResult> commentResults = commentToCommentResult.convertList(allComments);

        return commentResults;
    }
    // @RequestMapping(value = "/comment", method = RequestMethod.POST)
//    public String saveComment(@AuthenticationPrincipal User user,@Valid CommentForm commentForm){
//        Comment comment = new Comment();
//        Long idNote = commentForm.getNote_id();
//        comment.setAuthor(user);
//        if(postService.getById(idNote)!=null){
//        comment.setPost(postService.getById(idNote));
//        }
//        //если это комментарий на комментарий
//        if(commentService.getById(idNote)!=null){
//            Comment rootComment = commentService.getById(idNote);
//            comment.setReplyTo(rootComment);
//        }
//        comment.setText(commentForm.getText());
//        commentService.SaveOrUpdate(comment);
//        return "post/list";
//    }

    @RequestMapping("/comment/delete/{id}")
    public String delete(@PathVariable String id) {
        commentService.delete(Long.valueOf(id));
        return "redirect:/post/list";
    }
}
