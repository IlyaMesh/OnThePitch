package com.onthepitch.backend.controllers;

import com.onthepitch.backend.dao.PostRepository;
import com.onthepitch.backend.model.Post;

import com.onthepitch.shared.model.PostResult;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController
public class MessageController {

    private PostRepository postRepository;

   // private ObjectProvider<PostResult> provider;

//    @PostMapping("/factorialString")
//    public final String withInformation(@RequestBody final IncomeMessage message) {
//        return String.format("%s %d", message.getMessage());
//    }

    @Autowired
    public MessageController(PostRepository postRepository) {
        this.postRepository = postRepository;
      //  this.provider = provider;
    }

    @PostMapping({"/post/list", "/post"})
    @CrossOrigin
    public PostResult listProducts() {

        Post post = postRepository.findById(61L).get();
        PostResult postResult =new PostResult(
               post.getPost_id(),
               post.getAuthorName(),
               post.getHeader(),
               post.getText(),
               new Date()
        );
//        PostResult object = null;
//
//            object = this.provider.getObject(post.getPost_id(),
//                    post.getAuthorName(),
//                    post.getHeader(),
//                    post.getText(),
//                    new Date()
//            );
//            System.out.println(object.getHeader());
//

        return postResult;
    }

}
