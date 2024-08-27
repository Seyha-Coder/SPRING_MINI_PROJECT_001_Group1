package com.example.SPRING_MINI_PROJECT_001_Group1.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponseBookmark {
    private Integer articleId;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private Integer ownerOfArticle;
//    private List<CategoryIdListResponse> categoryList;
    List<Long> categoryList;
    private List<CommentListResponse> commentList;
}
