package com.onthepitch.backend.service.serviceImpl;

import com.onthepitch.backend.commands.PostForm;
import com.onthepitch.backend.converter.PostFormToPost;
import com.onthepitch.backend.repos.PostRepository;
import com.onthepitch.backend.model.Post;
import com.onthepitch.backend.model.User;
import com.onthepitch.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public Post getById(Long id) {
        return postRepository.findById(id).orElse(null);
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
    public Post saveOrUpdatePostForm(PostForm postForm, User user) {
        return null;
    }


}
