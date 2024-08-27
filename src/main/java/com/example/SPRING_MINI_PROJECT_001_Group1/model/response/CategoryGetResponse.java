package com.example.SPRING_MINI_PROJECT_001_Group1.model.response;

import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.CategoryArticle;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryGetResponse {
    private Long categoryId;;
    private String categoryName;
    private Integer amountOfArticles;
    private LocalDateTime createdAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime updatedAt;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CategoryArticleResponse> articleList;


}
