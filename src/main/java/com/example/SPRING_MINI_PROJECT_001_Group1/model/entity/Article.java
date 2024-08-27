package com.example.SPRING_MINI_PROJECT_001_Group1.model.entity;

import com.example.SPRING_MINI_PROJECT_001_Group1.model.response.CategoryArticleResponse;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.response.CategoryGetResponse;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "article_tb")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "article")
    private List<Bookmark> bookmark = new ArrayList<>();
    @OneToMany(mappedBy = "article")
    private List<Comment> comments = new ArrayList<>();
    @OneToMany(mappedBy = "articles")
    private List<CategoryArticle> categoryArticles = new ArrayList<>();

    public CategoryArticleResponse toResponse(){
        return new CategoryArticleResponse(this.id,this.title,this.description,this.createdAt,this.user.getId());
    }

}
