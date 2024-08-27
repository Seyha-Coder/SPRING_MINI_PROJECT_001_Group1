package com.example.SPRING_MINI_PROJECT_001_Group1.service.serviceImpl;

import com.example.SPRING_MINI_PROJECT_001_Group1.config.GetCurrentUser;
import com.example.SPRING_MINI_PROJECT_001_Group1.exception.CustomNotfoundException;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.ApiResponseBookmark;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.CommentListResponse;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Article;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Bookmark;
import com.example.SPRING_MINI_PROJECT_001_Group1.repository.ArticleRepository;
import com.example.SPRING_MINI_PROJECT_001_Group1.repository.BookmarkRepository;
import com.example.SPRING_MINI_PROJECT_001_Group1.repository.CategoryArticleRepository;
import com.example.SPRING_MINI_PROJECT_001_Group1.service.BookmarkService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookmarkServiceImplement implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final ArticleRepository articleRepository;
    private final GetCurrentUser currentUser;
    private final GetCurrentUser getCurrentUser;
    private final CategoryArticleRepository cateArtRepo;


    @Override
    public List<ApiResponseBookmark> getAllBookmarks(Integer pageNo, Integer pageSize, String sortBy, String sortDirection) {
        if (pageNo == null || pageNo < 0 || pageSize == null || pageSize <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid page number or page size");
        }

        if (sortBy == null || sortDirection == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid sort parameters");
        }

        Sort.Direction direction = "desc".equalsIgnoreCase(sortDirection) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Bookmark> allBookmarks = bookmarkRepository.findBookmarkByStatus(true, pageable);
        List<Bookmark> bookmarks = allBookmarks.getContent();

        List<ApiResponseBookmark> apiResponseBookmarks = new ArrayList<>();
        for (Bookmark bookmark : bookmarks) {
            ApiResponseBookmark responseBookmark = new ApiResponseBookmark();

            responseBookmark.setArticleId(Math.toIntExact(bookmark.getArticle().getId()));
            responseBookmark.setTitle(bookmark.getArticle().getTitle());
            responseBookmark.setDescription(bookmark.getArticle().getDescription());
            responseBookmark.setCreatedAt(bookmark.getArticle().getCreatedAt());
            responseBookmark.setOwnerOfArticle(Math.toIntExact(getCurrentUser.getCurrentUser().getId()));
            responseBookmark.setCategoryList(cateArtRepo.findCategoryListByArticleId(bookmark.getArticle().getId()));

            // Build the comment list
            List<CommentListResponse> listResponses = bookmark.getArticle().getComments()
                    .stream()
                    .map(comment -> new CommentListResponse(Math.toIntExact(comment.getId()), comment.getCmt(), comment.getCreatedAt()))
                    .collect(Collectors.toList());

            responseBookmark.setCommentList(listResponses);
            apiResponseBookmarks.add(responseBookmark);
        }

        return apiResponseBookmarks;
    }


    @Override
    public Bookmark addBookmark(Long articleId) {
        Long userId = currentUser.getCurrentUser().getId();
        boolean alreadyBookmarked = bookmarkRepository.existsByArticleIdAndUserId(articleId, userId);
        if (alreadyBookmarked) {
            throw new CustomNotfoundException("Article already bookmarked by user.");
        }
        Article article = articleRepository.findById(articleId).orElseThrow(
                () -> new CustomNotfoundException("Article not found with id " + articleId)
        );
        Bookmark bookmark = new Bookmark();
        bookmark.setStatus(true);
        bookmark.setCreatedAt(LocalDateTime.now());
        bookmark.setArticle(article);
        bookmark.setUser(currentUser.getCurrentUser());
        Bookmark savedBookmark = bookmarkRepository.save(bookmark);
        return savedBookmark;
    }

    @Override
    public Bookmark updateBookmark(Long articleId, Boolean status) {
       Bookmark bookmark = bookmarkRepository.findById(articleId).orElseThrow(
               () -> new CustomNotfoundException("Bookmark not found with id: " + articleId)
       );
       bookmark.setStatus(status);
       bookmark.setUpdatedAt(LocalDateTime.now());
       return bookmarkRepository.save(bookmark);
    }
}
