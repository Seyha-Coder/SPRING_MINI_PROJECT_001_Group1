package com.example.SPRING_MINI_PROJECT_001_Group1.controller;

import com.example.SPRING_MINI_PROJECT_001_Group1.model.ApiResponse;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.AppUserDto;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.dtoArticle.dtoArticleReponse.DTOArticleCommentResponse;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.dtoArticle.dtoArticleReponse.DTOResponseArticle;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.dtoArticle.dtoArticleReponse.DTOResponseArticleCre;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.dtoArticle.dtoArticleRequest.DTORequestArticle;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.dtoComment.dtoCommentRequest.DTOCommentRequest;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.dtoComment.dtoCommentResponse.DTOCommentResponse;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Article;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Comment;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.User;
import com.example.SPRING_MINI_PROJECT_001_Group1.service.AppUserService;
import com.example.SPRING_MINI_PROJECT_001_Group1.service.ArticleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
@SecurityRequirement(name = "bearerAuth")
public class ArticleController {
    private final ArticleService articleService;
    private final AppUserService appUserService;
    @PostMapping("/article")
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


    @GetMapping("/article")
    public ResponseEntity<ApiResponse<Object>> getAll(
            @RequestParam(defaultValue = "0", required = false) Integer pageNo,
            @RequestParam(defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(defaultValue = "id", required = false) String sortBy,
            @RequestParam(defaultValue = "ASC", required = false) Sort.Direction sortDirection
    ){
        List<DTOResponseArticle> getAllArticle = articleService.getAll(pageNo,pageSize,sortBy,sortDirection);
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .message("Get all article")
                .payload(getAllArticle)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(apiResponse);

    }

    @PostMapping("/post-comment/{id}")
    public ResponseEntity<ApiResponse<Object>> postComment(@PathVariable Long id, @RequestBody DTOCommentRequest dtoCommentRequest){
        DTOCommentResponse postComment = articleService.postComment(id,dtoCommentRequest);
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .message("A new comment is posted on article")
                .payload(postComment)
                .status(HttpStatus.CREATED)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getArticleById/{id}")
    public ResponseEntity<ApiResponse<Object>> getArticleById(@PathVariable Long id){
        DTOResponseArticle getArticle = articleService.getArticleById(id);
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .message("Get all comments on article id "+id+" successfully")
                .payload(getArticle)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/get-article/{id}")
    public ResponseEntity<ApiResponse<Object>> getCommentArticleById(@PathVariable Long id){
        DTOArticleCommentResponse getArticleComment = articleService.getCommentArticleById(id);
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .message("Get article with id "+id+" successfully")
                .payload(getArticleComment)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(apiResponse);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteArticle(@PathVariable Long id) throws Exception {
        Article deleteArticle = articleService.deleteArticle(id);
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .message("Deleted article by id "+id+" successful")
                .payload(null)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(apiResponse);
    }


    @PutMapping("/update/article/{id}")
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
