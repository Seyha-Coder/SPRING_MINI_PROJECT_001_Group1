package com.example.SPRING_MINI_PROJECT_001_Group1.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryArticleResponse {
    private Long articleId;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private Long ownerOfArticle;


}
