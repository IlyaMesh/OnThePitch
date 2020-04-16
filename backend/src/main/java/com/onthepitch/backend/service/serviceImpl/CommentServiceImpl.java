package com.onthepitch.backend.service.serviceImpl;

import com.onthepitch.backend.repos.CommentRepository;
import com.onthepitch.backend.model.Comment;
import com.onthepitch.backend.repos.RatingRepository;
import com.onthepitch.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class CommentServiceImpl implements CommentService {
    //TODO add deleting for rating
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public List<Comment> getAllCommentsForPost(long id) {
        List<Comment> comments = new ArrayList<>();
        commentRepository.findAllCommentsOfPost(id).forEach(comments :: add);
        return comments;
    }

    @Override
    public void saveOrUpdate(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public Comment getById(Long id) {
        if(id == null){
            return null;
        }
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if(comment.getComments().isEmpty()){
            commentRepository.deleteById(id);
        }
        else {
            comment.setText("This comment was deleted");
            commentRepository.save(comment);
        }
        ratingRepository.deleteByNote_id(id);
    }

}
