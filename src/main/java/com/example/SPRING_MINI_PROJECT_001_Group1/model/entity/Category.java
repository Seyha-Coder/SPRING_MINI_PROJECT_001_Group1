package com.example.SPRING_MINI_PROJECT_001_Group1.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    private String categoryName;
    private Float amountOfArticle;
    private LocalDateTime createAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime updateAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    @OneToMany(mappedBy = "categories")
    @JsonIgnore
    private List<CategoryArticle> category_article;
}
