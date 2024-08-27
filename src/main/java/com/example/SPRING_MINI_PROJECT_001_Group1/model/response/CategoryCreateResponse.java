package com.example.SPRING_MINI_PROJECT_001_Group1.model.response;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoryCreateResponse {
    private Integer categoryId;;
    private String categoryName;
    private LocalDateTime createAt;


}
