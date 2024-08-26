package com.example.SPRING_MINI_PROJECT_001_Group1.service;

import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Category;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.request.CategoryRequest;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.response.CategoryResponse;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CategoryService {

    Category getByIdCategory(Integer id);

    CategoryResponse createCategory(CategoryRequest categoryRequest);

    Category updateCategory(CategoryRequest categoryRequest, Integer id);

    Category deleteCategory(Integer id);

    List<Category> getAllCategory(Integer pageNo, Integer pageSize, String sortBy, Sort.Direction orderBy);
}
