package com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.dtoArticle.dtoArticleReponse;

import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Article;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Category;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.CategoryArticle;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DTOResponseArticle {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private Long ownerOfArticle;
    private List<Integer> categoryIdList;
    public void responseArticleWithCategoryIdList(Article article) {
        id = article.getId();
        title = article.getTitle();
        description = article.getDescription();
        createdAt = article.getCreatedAt();
        ownerOfArticle = article.getUser().getId();
        this.categoryIdList = article.getCategoryArticles().stream()
                .map(categoryArticle -> categoryArticle.getCategories().getCategoryId())
                .collect(Collectors.toList());
    }


}
