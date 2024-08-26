package com.example.SPRING_MINI_PROJECT_001_Group1.model.request;

import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Category;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoryRequest {
    private String categoryName;

    public Category toEntity(){
         return new Category(null,this.categoryName,0F,null,null,null,null);
    }
    public Category toEntity(Integer id,LocalDateTime createAt){
        return new Category(id,this.categoryName,0F,createAt, LocalDateTime.now(),null,null);
    }


}
