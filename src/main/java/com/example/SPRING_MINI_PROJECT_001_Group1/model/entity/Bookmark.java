package com.example.SPRING_MINI_PROJECT_001_Group1.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "bookmark_tb")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    private Long id;
    private Boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "article_id")
    private Article article;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

}
