package com.example.SPRING_MINI_PROJECT_001_Group1.service.serviceImpl;

import com.example.SPRING_MINI_PROJECT_001_Group1.config.GetCurrentUser;
import com.example.SPRING_MINI_PROJECT_001_Group1.exception.CustomNotfoundException;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.dtoComment.dtoCommentRequest.DTOCommentRequest;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Comment;
import com.example.SPRING_MINI_PROJECT_001_Group1.repository.CommentRepository;
import com.example.SPRING_MINI_PROJECT_001_Group1.service.CommentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final GetCurrentUser currentUser;

    public CommentServiceImpl(CommentRepository commentRepository, GetCurrentUser currentUser) {
        this.commentRepository = commentRepository;
        this.currentUser = currentUser;
    }

    @Override
    public Comment getCommentById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new CustomNotfoundException("The comment with id "+ id + " not found!")
        );
        return comment;
    }

    @Override
    public Comment updateComment(Long id, DTOCommentRequest request) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new CustomNotfoundException("The comment with id "+ id + " not found!")
        );
        Long userId =currentUser.getCurrentUser().getId();
        if(comment.getUser().getId() != userId ){
            throw new CustomNotfoundException("You don't have permission to access!");
        }
        comment.setUpdatedAt(LocalDateTime.now());
        comment.setCmt(request.getComment());
        commentRepository.save(comment);
        return comment;
    }

    @Override
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new CustomNotfoundException("The comment with id "+ id + " not found!")
        );
        Long userId =currentUser.getCurrentUser().getId();
        if(comment.getUser().getId() != userId ){
            throw new CustomNotfoundException("You don't have permission to access!");
        }
        commentRepository.deleteById(id);
    }
}
