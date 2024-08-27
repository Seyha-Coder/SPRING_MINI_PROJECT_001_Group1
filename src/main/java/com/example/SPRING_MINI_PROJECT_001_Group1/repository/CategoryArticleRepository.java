package com.example.SPRING_MINI_PROJECT_001_Group1.repository;

import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.CategoryArticle;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Article;
import java.util.List;

@Repository
public interface CategoryArticleRepository extends JpaRepository<CategoryArticle, Long> {

    @Query(value = """
        SELECT category_id
        FROM category_article
        WHERE article_id = :id
    """, nativeQuery = true)
    List<Long> findCategoryListByArticleId(Long id);
    @Transactional
    void deleteAllByArticles(Article article);

}
