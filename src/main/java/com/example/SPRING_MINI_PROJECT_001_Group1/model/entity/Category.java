package com.example.SPRING_MINI_PROJECT_001_Group1.model.entity;

import com.example.SPRING_MINI_PROJECT_001_Group1.model.response.CategoryArticleResponse;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.response.CategoryGetResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "category_tb")
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    private String categoryName;
    private Integer amountOfArticle;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "categories",cascade = CascadeType.ALL)
    private List<CategoryArticle> articleList;

    public CategoryGetResponse toResponse(){
        return new CategoryGetResponse(this.categoryId,this.categoryName, (int) this.articleList.stream().map(article -> article.getArticles().toResponse()).count(),this.createAt,
                this.updateAt,this.articleList.stream().map(article -> article.getArticles().toResponse()).toList());
    }
}
