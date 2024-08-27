package com.example.SPRING_MINI_PROJECT_001_Group1.repository;

import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Category;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Page<Category> findByUserId(Long userId, Pageable pageable);

}
