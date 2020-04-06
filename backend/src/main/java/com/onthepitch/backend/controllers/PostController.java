package com.onthepitch.backend.controllers;

import com.onthepitch.backend.converter.PostFormToPost;
import com.onthepitch.backend.converter.PostToPostForm;
import com.onthepitch.backend.model.Match;
import com.onthepitch.backend.model.User;
import com.onthepitch.backend.repos.PostRepository;
import com.onthepitch.backend.model.Post;
import com.onthepitch.backend.repos.UserRepo;
import com.onthepitch.shared.model.MatchesResult;
import com.onthepitch.shared.model.PostResult;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
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
private PostFormToPost postFormToPost;
    @Autowired
    private PostToPostForm postToPostForm;
    @Autowired
    private UserRepo userRepo;


    @Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    /**
     * Controller method for getting all posts
     * @return List of all posts
     */

    @GetMapping("/posts")
    public Page<PostResult> listPosts(@RequestParam(defaultValue = "0") int page,@RequestParam("size")int size) {
        List<PostResult> postResults = new ArrayList<PostResult>();
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Post> posts = postRepository.findAll(pageRequest);
        int totalElements = (int) posts.getTotalElements();
        return new PageImpl<PostResult>(
                posts.stream()
                .map(post -> postToPostForm.convert(post))
                .collect(Collectors.toList()),pageRequest,totalElements);
    }

    /**
     * Controller method for saving the post
     * @param post - new post that we'd like to save
     */
    @PostMapping("/posts")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('MODERATOR') or hasAuthority('ADMIN')")
    public void addPost(@RequestBody PostResult post) {
        String UserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByUsername(UserName);
        Post newPost = postFormToPost.convert(post);
        newPost.setAuthor(user);
        newPost.setCreated_at(new Date());
        postRepository.save(newPost);
    }

    @GetMapping("/post/{id}")
    public PostResult getPost(@PathVariable(name = "id") String id){
        Post post = postRepository.findById(Long.parseLong(id)).get();
        return postToPostForm.convert(post);
    }
}
