package com.example.SPRING_MINI_PROJECT_001_Group1.service;

import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Article;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.request.ArticleRequest;

public interface ArticleService {
    Article createArticle(ArticleRequest articleRequest);
}
