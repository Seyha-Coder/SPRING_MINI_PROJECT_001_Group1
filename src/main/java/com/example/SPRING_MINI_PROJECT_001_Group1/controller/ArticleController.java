package com.example.SPRING_MINI_PROJECT_001_Group1.controller;

import com.example.SPRING_MINI_PROJECT_001_Group1.model.response.ApiResponse;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.AppUserDto;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.dtoArticle.dtoArticleReponse.DTOArticleCommentResponse;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.dtoArticle.dtoArticleReponse.DTOResponseArticle;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.dtoArticle.dtoArticleReponse.DTOResponseArticleCre;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.dtoArticle.dtoArticleRequest.DTORequestArticle;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.dtoComment.dtoCommentRequest.DTOCommentRequest;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.dtoComment.dtoCommentResponse.DTOCommentResponse;
import com.example.SPRING_MINI_PROJECT_001_Group1.service.AppUserService;
import com.example.SPRING_MINI_PROJECT_001_Group1.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
@SecurityRequirement(name = "bearerAuth")
public class ArticleController {
    private final ArticleService articleService;
    private final AppUserService appUserService;
    @PostMapping("/article")
    @Operation(summary = "Create a new article",
            description = "The request has succeeded and a new resource has been created as a result.")
    public ResponseEntity<ApiResponse<Object>> postArticle(@RequestBody DTORequestArticle dtoRequestArticle) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        AppUserDto currentUser = appUserService.findUserByusername(username);
        DTOResponseArticleCre createdArticle = articleService.postArticle(dtoRequestArticle, currentUser);
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .message("A new article is created successfully.")
                .payload(createdArticle)
                .status(HttpStatus.CREATED)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/article/all")
    @Operation(summary = "Get all available articles ",
            description = "The request has succeeded and a new resource has been created as a result.")
    public ResponseEntity<ApiResponse<Object>> getAll(
            @Positive
            @RequestParam(defaultValue = "1", required = false) Integer pageNo,
            @RequestParam(defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(defaultValue = "id", required = false) String sortBy,
            @RequestParam(defaultValue = "ASC", required = false) Sort.Direction sortDirection
    ){
        List<DTOResponseArticle> getAllArticle = articleService.getAll(pageNo,pageSize,sortBy,sortDirection);
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .message("Get all article successful .")
                .payload(getAllArticle)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/post/article/{id}/comment")
    @Operation(summary = "Post a comment on any article via its id",
            description = "The request has succeeded and a new resource has been created as a result.")
    public ResponseEntity<ApiResponse<Object>> postComment(@PathVariable Long id, @RequestBody DTOCommentRequest dtoCommentRequest){
        DTOCommentResponse postComment = articleService.postComment(id,dtoCommentRequest);
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .message("A new comment is posted on article")
                .payload(postComment)
                .status(HttpStatus.CREATED)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/article/{id}/comment")
    @Operation(summary = "Get comment on any article",
            description = "The request has succeeded and a new resource has been created as a result.")
    public ResponseEntity<ApiResponse<Object>> getArticleById(@PathVariable Long id){
        DTOCommentResponse getArticle = articleService.getArticleById(id);
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .message("Get all comments on article id "+id+" successfully")
                .payload(getArticle)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/article/{id}")
    @Operation(summary = "Get article by id",
            description = "The request has succeeded and a new resource has been created as a result.")
    public ResponseEntity<ApiResponse<Object>> getCommentArticleById(@PathVariable Long id) {
        DTOArticleCommentResponse getArticleComment = articleService.getCommentArticleById(id);
            ApiResponse<Object> apiResponse = ApiResponse.builder()
                    .message("Get article with id " + id + " successfully")
                    .payload(getArticleComment)
                    .status(HttpStatus.OK)
                    .build();
            return ResponseEntity.ok(apiResponse);

    }

    @DeleteMapping("/author/article/{id}")
    @Operation(summary = "Delete article by id",
            description = "The request has succeeded and a new resource has been created as a result.")
    public ResponseEntity<ApiResponse<Object>> deleteArticle(@PathVariable Long id) throws Exception {
        articleService.deleteArticle(id);
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .message("Deleted article by id "+id+" successful")
                .payload(null)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/author/article/{id}")
    @Operation(summary = "Edit article by id",
            description = "The request has succeeded and a new resource has been created as a result.")
    public ResponseEntity<ApiResponse<Object>> update(@RequestBody DTORequestArticle dtoRequestArticle,@PathVariable Long id) throws Exception {
        DTOArticleCommentResponse updated = articleService.update(id,dtoRequestArticle);
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .message("Update article with id "+id+" successfully")
                .payload(updated)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}
