package com.example.SPRING_MINI_PROJECT_001_Group1.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private List<Bookmark> bookmark = new ArrayList<>();
    @OneToMany(mappedBy = "article")
    private List<Comment> comments = new ArrayList<>();
    @OneToMany(mappedBy = "articles")
    private List<CategoryArticle> categoryArticles = new ArrayList<>();

}
