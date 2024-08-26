package com.example.SPRING_MINI_PROJECT_001_Group1.service.serviceImpl;

import com.example.SPRING_MINI_PROJECT_001_Group1.config.GetCurrentUser;
import com.example.SPRING_MINI_PROJECT_001_Group1.exception.CustomNotfoundException;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Category;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.User;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.request.CategoryRequest;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.response.CategoryResponse;
import com.example.SPRING_MINI_PROJECT_001_Group1.repository.CategoryRepository;
import com.example.SPRING_MINI_PROJECT_001_Group1.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImp implements CategoryService {
    private CategoryRepository categoryRepository;

    private GetCurrentUser currentUser;

    @Override
    public Category getByIdCategory(Integer id) {
        long userId = currentUser.getCurrentUser().getId();
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new CustomNotfoundException("Category id "+ id + "not found.")
        );
        if(category.getUser().getId() != userId){
            throw new CustomNotfoundException("You don't have permission to access.");
        }
        return category;
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        Category category = categoryRequest.toEntity();
        category.setCreateAt(LocalDateTime.now());
        category.setUser(currentUser.getCurrentUser());
        Category savaCategory = categoryRepository.save(category);
        return new CategoryResponse(savaCategory.getCategoryId(),savaCategory.getCategoryName(),savaCategory.getCreateAt());
    }

    @Override
    public Category updateCategory(CategoryRequest categoryRequest, Integer id) {
        Category getCategoryById = categoryRepository.findById(id).orElseThrow(
                () -> new CustomNotfoundException("Category id "+ id + "not found.")
        );
        return categoryRepository.save(categoryRequest.toEntity(id,getCategoryById.getCreateAt(),currentUser.getCurrentUser()));
    }

    @Override
    public Category deleteCategory(Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new CustomNotfoundException("Category id "+ id + "not found.")
        );
        return category;
    }

    @Override
    public List<Category> getAllCategory(Integer pageNo, Integer pageSize, String sortBy, Sort.Direction orderBy) {
        Long userId = currentUser.getCurrentUser().getId();
        Pageable pageable = PageRequest.of(pageNo,pageSize,Sort.by(orderBy,sortBy));
        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories.getContent();
    }

}
