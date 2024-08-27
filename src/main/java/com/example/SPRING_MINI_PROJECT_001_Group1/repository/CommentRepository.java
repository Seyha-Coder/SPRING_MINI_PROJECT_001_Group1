package com.example.SPRING_MINI_PROJECT_001_Group1.repository;

import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
