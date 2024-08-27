package com.example.SPRING_MINI_PROJECT_001_Group1.service;

import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Category;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.request.CategoryRequest;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.response.CategoryCreateResponse;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.response.CategoryGetResponse;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CategoryService {

    Category getByIdCategory(Integer id);

    CategoryCreateResponse createCategory(CategoryRequest categoryRequest);

    Category updateCategory(CategoryRequest categoryRequest, Integer id);

    Category deleteCategory(Integer id);

    List<CategoryGetResponse> getAllCategory(Integer pageNo, Integer pageSize, String sortBy, Sort.Direction orderBy);
}
