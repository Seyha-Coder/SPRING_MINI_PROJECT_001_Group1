package com.example.SPRING_MINI_PROJECT_001_Group1.controller;

import com.example.SPRING_MINI_PROJECT_001_Group1.model.response.ApiResponse;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.dtoComment.dtoCommentRequest.DTOCommentRequest;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Comment;
import com.example.SPRING_MINI_PROJECT_001_Group1.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/comment")
@SecurityRequirement(name = "bearerAuth")
public class CommentController {
    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }
    @Operation(summary = "get comment by its id")
    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> getCommentById(@Positive @PathVariable Long id){
        Comment comment = service.getCommentById(id);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Get comment successfully")
                .status(HttpStatus.OK)
                .code(200)
                .payload(comment)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @Operation(summary = "update comment by its id")
    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> updateCommentById(@Positive @PathVariable Long id, @RequestBody DTOCommentRequest commentRequest){
        Comment comment = service.updateComment(id,commentRequest);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Comment with id "+ id + " updated successfully.")
                .status(HttpStatus.OK)
                .code(200)
                .payload(comment)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @Operation(summary = "delete comment by its id")
    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteCommentById(@Positive @PathVariable Long id){
        service.deleteComment(id);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Get comment successfully")
                .status(HttpStatus.OK)
                .code(200)
                .payload(null)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
