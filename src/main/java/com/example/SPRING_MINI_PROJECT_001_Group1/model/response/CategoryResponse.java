package com.example.SPRING_MINI_PROJECT_001_Group1.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoryResponse {
    private Integer categoryId;;
    private String categoryName;
    private LocalDateTime createAt;


}
