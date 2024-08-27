package com.example.SPRING_MINI_PROJECT_001_Group1.service;

import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.dtoComment.dtoCommentRequest.DTOCommentRequest;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Comment;

public interface CommentService {
    Comment getCommentById(Long id);
    Comment updateComment(Long id, DTOCommentRequest request);
    void deleteComment(Long id);
}
