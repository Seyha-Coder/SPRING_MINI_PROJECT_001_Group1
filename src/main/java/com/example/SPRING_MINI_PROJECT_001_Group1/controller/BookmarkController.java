package com.example.SPRING_MINI_PROJECT_001_Group1.controller;

import com.example.SPRING_MINI_PROJECT_001_Group1.model.response.ApiResponse;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.response.ApiResponseBookmark;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Bookmark;
import com.example.SPRING_MINI_PROJECT_001_Group1.service.BookmarkService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/bookmark")
@SecurityRequirement(name = "bearerAuth")
public class BookmarkController {

    private final BookmarkService bookmarkService;


    @GetMapping
    public ResponseEntity<?> getAllBookmarks(
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

    @PostMapping("/{id}")
    public ResponseEntity<?> createBookmark(@PathVariable Long id) {
        Bookmark bookmark = bookmarkService.addBookmark(id);
        ApiResponse<Object> apiResponse = ApiResponse
                .builder()
                .message("Create a bookmark id " + id +" successfully")
                .status(HttpStatus.CREATED)
                .payload(bookmark)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBookmark(@PathVariable Long id, @RequestParam Boolean status) {
        Bookmark bookmark = bookmarkService.updateBookmark(id, status);
        ApiResponse<Object> apiResponse = ApiResponse
                .builder()
                .message("Update article id " + id +" is unmarked successfully")
                .status(HttpStatus.OK)
                .payload(bookmark)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}
