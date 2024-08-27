package com.example.SPRING_MINI_PROJECT_001_Group1.service.serviceImpl;

import com.example.SPRING_MINI_PROJECT_001_Group1.config.GetCurrentUser;
import com.example.SPRING_MINI_PROJECT_001_Group1.exception.CustomNotfoundException;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Category;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.User;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.request.CategoryRequest;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.response.CategoryCreateResponse;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.response.CategoryGetResponse;
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
    public Category getByIdCategory(Long id) {
        Long userId= currentUser.getCurrentUser().getId();
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new CustomNotfoundException("Category id "+ id + "not found.")
        );
        if(category.getUser().getId() != userId){
            throw new CustomNotfoundException(" You have no permission to access!");
        }
        return category;
    }


    @Override
    public CategoryCreateResponse createCategory(CategoryRequest categoryRequest) {

        Long userId = currentUser.getCurrentUser().getId();
        if(!currentUser.getCurrentUser().getRole().equals("AUTHOR")){
            throw new CustomNotfoundException("Only Author can create category.");
        }
        if(currentUser.getCurrentUser().getRole().equalsIgnoreCase("READER")){
            throw new CustomNotfoundException("Only Author can create category.");
        }
        boolean exists = categoryRepository.existsByCategoryNameAndUserId(categoryRequest.getCategoryName(), userId);
        if (exists) {
            throw new CustomNotfoundException("Category name can't be duplicate");
        }
        Category category = categoryRequest.toEntity();
        category.setCreateAt(LocalDateTime.now());
        category.setUser(currentUser.getCurrentUser());
        Category savaCategory = categoryRepository.save(category);
        return new CategoryCreateResponse(savaCategory.getCategoryId(),savaCategory.getCategoryName(),savaCategory.getCreateAt());
    }

    @Override
    public Category updateCategory(CategoryRequest categoryRequest, Long id) {
        Long userId = currentUser.getCurrentUser().getId();
        Category getCategoryById = categoryRepository.findById(id).orElseThrow(
                () -> new CustomNotfoundException("Category id "+ id + " not found.")
        );
        if(getCategoryById.getUser().getId() != userId){
            throw new CustomNotfoundException("You have no permission to access!");
        }
        return categoryRepository.save(categoryRequest.toEntity(id,getCategoryById.getCreateAt(),currentUser.getCurrentUser()));

    }

    @Override
    public void deleteCategory(Long id) {
        Long userId = currentUser.getCurrentUser().getId();
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new CustomNotfoundException("Category id "+ id + " not found.")
        );
        if(category.getUser().getId() != userId){
            throw new CustomNotfoundException("You have no permission to access!");
        }
        categoryRepository.deleteById(id);

    }


    @Override
    public List<CategoryGetResponse> getAllCategory(Integer pageNo, Integer pageSize, String sortBy, Sort.Direction orderBy) {
        User user = currentUser.getCurrentUser();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(orderBy, sortBy));
        Page<Category> categories = categoryRepository.findByUserId(user.getId(), pageable);
        return categories.getContent().stream().map(Category::toResponse).toList();
    }


}
