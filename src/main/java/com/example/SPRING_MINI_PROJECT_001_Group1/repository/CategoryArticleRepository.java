package com.example.SPRING_MINI_PROJECT_001_Group1.repository;

import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Article;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.CategoryArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CategoryArticleRepository extends JpaRepository<CategoryArticle,Long> {

    @Transactional
    void deleteAllByArticles(Article article);

}
