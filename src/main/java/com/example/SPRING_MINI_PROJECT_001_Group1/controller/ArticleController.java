package com.example.SPRING_MINI_PROJECT_001_Group1.controller;

import com.example.SPRING_MINI_PROJECT_001_Group1.model.ApiResponseArticle;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Article;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.request.ArticleRequest;
import com.example.SPRING_MINI_PROJECT_001_Group1.service.ArticleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/author/article")
@SecurityRequirement(name = "bearerAuth")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public ResponseEntity<?> createArticle(@RequestBody ArticleRequest articleRequest){
        Article article = articleService.createArticle(articleRequest);
        ApiResponseArticle<?> apiResponseArticle = ApiResponseArticle.builder()
                .message("Successfully!")
                .status(HttpStatus.CREATED)
                .payload(article)
                .build();
        return ResponseEntity.ok(apiResponseArticle);
    }
}
