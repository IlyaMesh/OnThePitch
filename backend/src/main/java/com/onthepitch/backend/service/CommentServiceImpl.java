package com.onthepitch.backend.service;

import com.onthepitch.backend.repos.CommentRepository;
import com.onthepitch.backend.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Override
    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<>();
       // commentRepository.findAll().forEach(comments :: add);
        return comments;
    }

    @Override
    public List<Comment> getAllCommentsForPost(long id) {
        List<Comment> comments = new ArrayList<>();
        commentRepository.findAllCommentsOfPost(id).forEach(comments :: add);
        return comments;
    }

    @Override
    public void SaveOrUpdate(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public Comment getById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

}
