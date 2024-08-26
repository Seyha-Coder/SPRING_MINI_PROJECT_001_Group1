package com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.dtoArticle.dtoArticleRequest;

import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Article;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.CategoryArticle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DTORequestArticle {
    private String title;
    private String description;
    private List<Long> categoryId;
    public void requestArticle(Article article, List<CategoryArticle> categoryArticles) {
        article.setTitle(this.title);
        article.setDescription(this.description);
        article.setCategoryArticles(categoryArticles);
    }


}
