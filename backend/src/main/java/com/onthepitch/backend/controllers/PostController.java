package com.onthepitch.backend.controllers;

import com.onthepitch.backend.dao.PostRepository;
import com.onthepitch.backend.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Class for posts controllers
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {

    private PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * Controller method for getting all posts
     * @return List of all posts
     */
    @GetMapping("/posts")
    public List<Post> listPosts() {
        return StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    /**
     * Controller method for saving the post
     * @param post - new post that we'd like to save
     */
    @PostMapping("/posts")
    public void addPost(@RequestBody Post post) {
        postRepository.save(post);
    }
}
