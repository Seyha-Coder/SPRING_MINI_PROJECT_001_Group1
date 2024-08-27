package com.example.SPRING_MINI_PROJECT_001_Group1.service;

import com.example.SPRING_MINI_PROJECT_001_Group1.model.ApiResponseBookmark;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Bookmark;

import java.util.List;

public interface BookmarkService {
    List<ApiResponseBookmark> getAllBookmarks(Integer pageNo, Integer pageSize, String sortBy, String sortDirection);
    Bookmark addBookmark(Long bookmarkId);
    Bookmark updateBookmark(Long bookmarkId, Boolean status);
}
