package com.example.SPRING_MINI_PROJECT_001_Group1.controller;

import com.example.SPRING_MINI_PROJECT_001_Group1.config.GetCurrentUser;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.ApiResponse;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.ApiResponseBookmark;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.CategoryIdListResponse;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.CommentListResponse;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Bookmark;
import com.example.SPRING_MINI_PROJECT_001_Group1.repository.BookmarkRepository;
import com.example.SPRING_MINI_PROJECT_001_Group1.repository.CategoryArticleRepository;
import com.example.SPRING_MINI_PROJECT_001_Group1.service.BookmarkService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/bookmark")
@SecurityRequirement(name = "bearerAuth")
public class BookmarkController {

    private final BookmarkService bookmarkService;


    @GetMapping
    public ResponseEntity<?> getAllBookmarks(
                                           @Positive
                                           @RequestParam(defaultValue = "0") Integer pageNo,
                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                           @RequestParam(defaultValue = "articleId") String sortBy,
                                           @RequestParam(defaultValue = "DESC") String direction) {

        List<ApiResponseBookmark> bookmarks = bookmarkService.getAllBookmarks(pageNo, pageSize, sortBy, direction);
        ApiResponse<?> response = ApiResponse
                .builder()
                .message("Get all bookmarked articles successfully")
                .status(HttpStatus.OK)
                .payload(bookmarks)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{bookmarkId}")
    public ResponseEntity<?> createBookmark(@PathVariable Long bookmarkId) {
        Bookmark bookmark = bookmarkService.addBookmark(bookmarkId);
        ApiResponse<Object> apiResponse = ApiResponse
                .builder()
                .message("Create a bookmark id " + bookmarkId +" successfully")
                .status(HttpStatus.CREATED)
                .payload(bookmark)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{bookmarkId}")
    public ResponseEntity<?> updateBookmark(@PathVariable Long bookmarkId, @RequestParam Boolean status) {
        Bookmark bookmark = bookmarkService.updateBookmark(bookmarkId, status);
        ApiResponse<Object> apiResponse = ApiResponse
                .builder()
                .message("Update article id " + bookmarkId +" is unmarked successfully")
                .status(HttpStatus.OK)
                .payload(bookmark)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}
