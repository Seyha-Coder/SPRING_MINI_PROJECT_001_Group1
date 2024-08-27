package com.example.SPRING_MINI_PROJECT_001_Group1.controller;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Category;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.ApiResponseCategory;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.request.CategoryRequest;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.response.CategoryCreateResponse;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.response.CategoryGetResponse;
import com.example.SPRING_MINI_PROJECT_001_Group1.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/author/category")
@SecurityRequirement(name = "bearerAuth")

public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @Operation(summary = "Get all category")
    public ResponseEntity<ApiResponseCategory<List<CategoryGetResponse>>> getAllCategory(@Positive @RequestParam (defaultValue = "1") Integer pageNo,
                                                                                         @RequestParam (defaultValue = "10") Integer pageSize,
                                                                                         @RequestParam (defaultValue = "categoryId") String sortBy,
                                                                                         @RequestParam Sort.Direction orderBy){
        List<CategoryGetResponse> categories = categoryService.getAllCategory(pageNo, pageSize, sortBy, orderBy);
        ApiResponseCategory<List<CategoryGetResponse>> apiResponseCategory = ApiResponseCategory.<List<CategoryGetResponse>>builder()
                .message("Get all categories successfully.")
                .status(HttpStatus.OK)
                .payload(categories)
                .build();
        return ResponseEntity.ok(apiResponseCategory);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category by id")
    public ResponseEntity<ApiResponseCategory<Category>> getByIdCategory(@PathVariable Integer id){
        ApiResponseCategory<Category> apiResponseCategory = ApiResponseCategory.<Category>builder()
                .message("Get category with id 16 successfully.")
                .status(HttpStatus.OK)
                .payload(categoryService.getByIdCategory(id))
                .build();
        return ResponseEntity.ok(apiResponseCategory);
    }

    @PostMapping
    @Operation(summary = "Create a new category")
    public ResponseEntity<ApiResponseCategory<CategoryCreateResponse>> createCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        ApiResponseCategory<CategoryCreateResponse> apiResponseCategory = ApiResponseCategory.<CategoryCreateResponse>builder()
                .message("A new category is created successfully.")
                .status(HttpStatus.CREATED)
                .payload(categoryService.createCategory(categoryRequest))
                .build();
        return ResponseEntity.ok(apiResponseCategory);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update category by id")
    public ResponseEntity<ApiResponseCategory<Category>> updateCategory(@RequestBody CategoryRequest categoryRequest , @PathVariable Integer id){
        ApiResponseCategory<Category> apiResponseCategory = ApiResponseCategory.<Category>builder()
                .message("Category updated successfully.")
                .status(HttpStatus.OK)
                .payload(categoryService.updateCategory(categoryRequest,id))
                .build();
        return ResponseEntity.ok(apiResponseCategory);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete category by id")
    public ResponseEntity<ApiResponseCategory<Category>> deleteCategory(@PathVariable Integer id){
        ApiResponseCategory<Category> apiResponseCategory = ApiResponseCategory.<Category>builder()
                .message("Category deleted successfully.")
                .status(HttpStatus.OK)
                .payload(null)
                .build();
        return ResponseEntity.ok(apiResponseCategory);
    }
}
