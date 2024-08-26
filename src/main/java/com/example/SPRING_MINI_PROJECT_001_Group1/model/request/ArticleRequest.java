package com.example.SPRING_MINI_PROJECT_001_Group1.model.request;

import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ArticleRequest {
    private String title;
    private String description;
    private Integer categoryId;


    public Article toEntity(){
        return new Article(null,this.title,this.description,null,null,null,null,null,null);
    }
}
