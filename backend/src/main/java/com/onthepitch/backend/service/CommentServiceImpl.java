package com.onthepitch.backend.service;

import com.onthepitch.backend.dao.CommentRepository;
import com.onthepitch.backend.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
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
