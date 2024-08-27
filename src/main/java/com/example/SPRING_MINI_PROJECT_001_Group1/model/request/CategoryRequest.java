package com.example.SPRING_MINI_PROJECT_001_Group1.model.request;

import com.example.SPRING_MINI_PROJECT_001_Group1.config.GetCurrentUser;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Category;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoryRequest {
    @NotBlank(message = "Category name may not be null")
    @NotNull
    private String categoryName;


    public Category toEntity(){
         return new Category(null,this.categoryName,0,null,null,null,null);
    }
    public Category toEntity(Integer id, LocalDateTime createAt, User user){
        return new Category(id,this.categoryName,0,createAt, LocalDateTime.now(),user,null);
    }

}
