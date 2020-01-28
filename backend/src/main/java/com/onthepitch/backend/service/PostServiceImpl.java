package com.onthepitch.backend.service;

import com.onthepitch.backend.commands.PostForm;
import com.onthepitch.backend.converter.PostFormToPost;
import com.onthepitch.backend.dao.PostRepository;
import com.onthepitch.backend.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    private PostFormToPost postFormToPost;

    @Override
    public List<Post> listAll() {
        List<Post> posts = new ArrayList<>();
        postRepository.findAll().forEach(posts::add);
        return posts;
    }

    @Autowired
    public PostServiceImpl(PostRepository postRepository, PostFormToPost postFormToPost) {
        this.postRepository = postRepository;
        this.postFormToPost = postFormToPost;
    }

    @Override
    public Optional<Post> getById(Long id) {
        return postRepository.findById(id);
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
    public Post saveOrUpdatePostForm(PostForm postForm) {
        Post savedPost = saveOrUpdate(postFormToPost.convert(postForm));
        System.out.println("Saved product id+ "+savedPost.getPost_id());
        return savedPost;
    }


}
