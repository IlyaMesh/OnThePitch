package com.onthepitch.backend.controllers;

import com.onthepitch.backend.service.LogService;
import com.onthepitch.backend.service.PostService;
import com.onthepitch.shared.model.request.FilterRequest;
import com.onthepitch.shared.model.response.MessageResponse;
import com.onthepitch.shared.model.response.PostResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Class for posts controllers
 */
@RestController
@CrossOrigin(origins = "*")
public class PostController {

    private final PostService postService;
    private final LogService logService;

    @Autowired
    public PostController(PostService postService, LogService logService) {
        this.postService = postService;
        this.logService = logService;
    }

    /**
     * Controller method for getting all posts
     *
     * @return Page of all posts
     */

    @GetMapping("/posts")
    public Page<PostResult> listPosts(@RequestParam(defaultValue = "0") int page, @RequestParam("size") int size) {
        return postService.listAll(page, size);
    }

    @PostMapping("/posts/filtered")
    public Page<PostResult> getFilteredPosts(@RequestParam(defaultValue = "0") int page, @RequestParam("size") int size,@RequestBody FilterRequest req) {
        return postService.listAll(page, size,req.getText());
    }

    /**
     * Controller method for saving the post
     *
     * @param post - new post that we'd like to save
     */
    @PostMapping("/posts")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('MODERATOR') or hasAuthority('ADMIN')")
    public ResponseEntity<?> addPost(@RequestBody PostResult post) {
        postService.addPost(post);
        return ResponseEntity.ok(new MessageResponse("Post created successfully"));
    }

    @GetMapping("/post/{id}")
    public PostResult getPost(@PathVariable(name = "id") String id) {
        return postService.getById(Long.parseLong(id));
    }

    @DeleteMapping("/posts/{id}")
    @PreAuthorize("hasAuthority('MODERATOR') or hasAuthority('ADMIN')")
    public void deletePost(@PathVariable("id") String id) {
        postService.delete(Long.parseLong(id));
        logService.addLog("Delete post");
    }
}
