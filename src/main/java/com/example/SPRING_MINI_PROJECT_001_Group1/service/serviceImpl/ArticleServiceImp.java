package com.example.SPRING_MINI_PROJECT_001_Group1.service.serviceImpl;

import com.example.SPRING_MINI_PROJECT_001_Group1.exception.CustomNotfoundException;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Article;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Category;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.request.ArticleRequest;
import com.example.SPRING_MINI_PROJECT_001_Group1.repository.ArticleRepository;
import com.example.SPRING_MINI_PROJECT_001_Group1.service.ArticleService;
import com.example.SPRING_MINI_PROJECT_001_Group1.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleServiceImp implements ArticleService {

    private final ArticleRepository articleRepository;
    private final CategoryService categoryService;

    public ArticleServiceImp(ArticleRepository articleRepository, CategoryService categoryService) {
        this.articleRepository = articleRepository;
        this.categoryService = categoryService;
    }

    @Override
    public Article createArticle(ArticleRequest articleRequest) {
        Optional<Category> category = Optional.ofNullable(categoryService.getByIdCategory(articleRequest.getCategoryId()));
        if(!category.isPresent()){
            throw new CustomNotfoundException("Not Found!");
        }
        return articleRepository.save(articleRequest.toEntity());
    }
}