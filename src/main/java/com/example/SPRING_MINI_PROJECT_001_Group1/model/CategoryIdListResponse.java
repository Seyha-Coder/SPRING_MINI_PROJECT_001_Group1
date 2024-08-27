package com.example.SPRING_MINI_PROJECT_001_Group1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryIdListResponse {
    private Integer categoryIdList;
}
