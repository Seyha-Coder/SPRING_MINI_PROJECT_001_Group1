package com.example.SPRING_MINI_PROJECT_001_Group1.controller;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Category;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.ApiResponseCategory;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.request.CategoryRequest;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.response.CategoryResponse;
import com.example.SPRING_MINI_PROJECT_001_Group1.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
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
    public ResponseEntity<ApiResponseCategory<List<Category>>> getAllCategory(@RequestParam (defaultValue = "0") Integer pageNo,
                                            @RequestParam (defaultValue = "10") Integer pageSize,
                                            @RequestParam (defaultValue = "categoryId") String sortBy,
                                            @RequestParam Sort.Direction orderBy){
        ApiResponseCategory<List<Category>> apiResponseCategory = ApiResponseCategory.<List<Category>>builder()
                .message("Get all categories successfully.")
                .status(HttpStatus.OK)
                .payload(categoryService.getAllCategory(pageNo,pageSize,sortBy,orderBy))
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
    public ResponseEntity<ApiResponseCategory<CategoryResponse>> createCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        ApiResponseCategory<CategoryResponse> apiResponseCategory = ApiResponseCategory.<CategoryResponse>builder()
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
