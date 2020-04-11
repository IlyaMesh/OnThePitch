package com.onthepitch.backend.controllers;

import com.onthepitch.backend.service.LogService;
import com.onthepitch.backend.service.PostService;
import com.onthepitch.shared.model.MessageResponse;
import com.onthepitch.shared.model.PostResult;
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
//        PageRequest pageRequest = PageRequest.of(page, size);
//        Page<Post> posts = postRepository.findAll(pageRequest);
//        int totalElements = (int) posts.getTotalElements();
//        return new PageImpl<>(
//                posts.stream()
//                        .map(post -> postToPostForm.convert(post))
//                        .collect(Collectors.toList()), pageRequest, totalElements);
        return postService.listAll(page, size);
    }

    /**
     * Controller method for saving the post
     *
     * @param post - new post that we'd like to save
     */
    @PostMapping("/posts")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('MODERATOR') or hasAuthority('ADMIN')")
    public ResponseEntity<?> addPost(@RequestBody PostResult post) {
//        String UserName = SecurityContextHolder.getContext().getAuthentication().getName();
//        User user = userRepo.findByUsername(UserName);
        postService.addPost(post);
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Post newPost = postFormToPost.convert(post);
//        newPost.setAuthor(user);
//        newPost.setCreated_at(new Date());
//        postRepository.save(newPost);
        return ResponseEntity.ok(new MessageResponse("Post created successfully"));
    }

    @GetMapping("/post/{id}")
    public PostResult getPost(@PathVariable(name = "id") String id) {
//        Post post = postRepository.findById(Long.parseLong(id)).get();
//        return postToPostForm.convert(post);
        return postService.getById(Long.parseLong(id));
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable("id") String id) {
        logService.addLog("Delete post");
        postService.delete(Long.parseLong(id));
        //postRepository.deleteById(Long.parseLong(id));
    }
}
