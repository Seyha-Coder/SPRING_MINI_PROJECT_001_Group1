package com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.dtoArticle.dtoArticleReponse;

import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOResponseArticleCre {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private Long ownerOfArticle;

    public void responseArticle(Article article) {
        id = article.getId();
        title = article.getTitle();
        description = article.getDescription();
        createdAt = article.getCreatedAt();
        ownerOfArticle = article.getUser().getId();
    }
}
