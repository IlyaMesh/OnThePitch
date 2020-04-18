package com.onthepitch.backend.service.serviceImpl;

import com.onthepitch.backend.converter.PostFormToPost;
import com.onthepitch.backend.converter.PostToPostForm;
import com.onthepitch.backend.exсeption.NoSuchPostException;
import com.onthepitch.backend.model.Post;
import com.onthepitch.backend.model.User;
import com.onthepitch.backend.repos.PostRepository;
import com.onthepitch.backend.service.PostService;
import com.onthepitch.shared.model.response.PostResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostToPostForm postToPostForm;
    private final PostFormToPost postFormToPost;

    @Autowired
    public PostServiceImpl(PostRepository postRepository,PostToPostForm postToPostForm, PostFormToPost postFormToPost) {
        this.postRepository = postRepository;
        this.postToPostForm = postToPostForm;
        this.postFormToPost = postFormToPost;
    }

    @Override
    public Page<PostResult> listAll(int page, int size, String text) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Post> first = postRepository.findPostLike(text, pageRequest);
        int totalElements = (int) first.getTotalElements();
        return new PageImpl<>(
                first.stream()
                        .map(post -> postToPostForm.convert(post))
                        .collect(Collectors.toList()), pageRequest, totalElements);
    }

    @Override
    public Page<PostResult> listAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Post> posts = postRepository.findAll(pageRequest);
        int totalElements = (int) posts.getTotalElements();
        return new PageImpl<>(
                posts.stream()
                        .map(post -> postToPostForm.convert(post))
                        .collect(Collectors.toList()), pageRequest, totalElements);
    }

    @Override
    public PostResult getById(Long id) {
//        Post post = postRepository.findById(Long.parseLong(id)).get();
//        return postToPostForm.convert(post);
        Post post = postRepository.findById(id).orElse(null);
        if(post == null){
            throw new NoSuchPostException();
        }
        return postToPostForm.convert(post);
    }

    @Override
    public Post saveOrUpdate(Post post) {
        postRepository.save(post);
        return post;
    }

    @Override
    public void delete(Long id) {

        postRepository.deleteById(id);
    }

    @Override
    public void addPost(PostResult post) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post newPost = postFormToPost.convert(post);
        newPost.setAuthor(user);
        newPost.setCreated_at(new Date());
        postRepository.save(newPost);
    }

    @Override
    public Post getPostById(Long parseLong) {
        Post post = postRepository.findById(parseLong).orElse(null);
        if(post == null){
            throw new NoSuchPostException();
        }
        return post;
    }


}
