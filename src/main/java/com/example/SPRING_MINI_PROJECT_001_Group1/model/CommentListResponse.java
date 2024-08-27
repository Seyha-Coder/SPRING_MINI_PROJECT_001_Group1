package com.example.SPRING_MINI_PROJECT_001_Group1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentListResponse {
    private Integer commentId;
    private String comment;
    private LocalDateTime createdAt;
}
