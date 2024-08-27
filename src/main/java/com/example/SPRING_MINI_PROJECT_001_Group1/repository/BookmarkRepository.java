package com.example.SPRING_MINI_PROJECT_001_Group1.repository;

import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Bookmark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Page<Bookmark> findBookmarkByStatus(Boolean status, Pageable pageable);
}
