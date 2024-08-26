package com.example.SPRING_MINI_PROJECT_001_Group1.service.serviceImpl;

import com.example.SPRING_MINI_PROJECT_001_Group1.config.GetCurrentUser;
import com.example.SPRING_MINI_PROJECT_001_Group1.exception.CustomNotfoundException;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.AppUserDto;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.dtoArticle.dtoArticleReponse.DTOArticleCommentResponse;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.dtoArticle.dtoArticleReponse.DTOResponseArticle;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.dtoArticle.dtoArticleReponse.DTOResponseArticleCre;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.dtoArticle.dtoArticleRequest.DTORequestArticle;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.dtoComment.dtoCommentRequest.DTOCommentRequest;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.dtoComment.dtoCommentResponse.DTOCommentResponse;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.*;
import com.example.SPRING_MINI_PROJECT_001_Group1.repository.ArticleRepository;
import com.example.SPRING_MINI_PROJECT_001_Group1.repository.CategoryArticleRepository;
import com.example.SPRING_MINI_PROJECT_001_Group1.repository.CategoryRepository;
import com.example.SPRING_MINI_PROJECT_001_Group1.repository.CommentRepository;
import com.example.SPRING_MINI_PROJECT_001_Group1.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ArticleServiceImp implements ArticleService {
    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final GetCurrentUser getCurrentUser;
    private final CommentRepository commentRepository;
    private final CategoryArticleRepository categoryArticleRepository;

    private User convertToUserEntity(AppUserDto appUserDto) {
        User user = new User();
        user.setId(appUserDto.getId());
        user.setUsername(appUserDto.getUsername());
        return user;
    }

    @Override
    public DTOResponseArticleCre postArticle(DTORequestArticle dtoRequestArticle, AppUserDto currentUserDto) throws Exception {
        User currentUser = convertToUserEntity(currentUserDto);
        List<Category> categories = categoryRepository.findAllById(dtoRequestArticle.getCategoryId());
        if (!getCurrentUser.getCurrentUser().getRole().equals("AUTHOR")) {
            throw new Exception("User reader can not post article");
        }
        Article article = new Article();
        article.setTitle(dtoRequestArticle.getTitle());
        article.setDescription(dtoRequestArticle.getDescription());
        article.setUser(currentUser);
        article.setCreatedAt(LocalDateTime.now());

        for (Category category : categories) {
            CategoryArticle categoryArticle = new CategoryArticle();
            categoryArticle.setArticles(article);
            categoryArticle.setCategories(category);
            article.getCategoryArticles().add(categoryArticle);
            category.getCategoryArticle().add(categoryArticle);
        }
        // Save the article
        Article savedArticle = articleRepository.save(article);
        // Map the saved article to a DTO response
        DTOResponseArticleCre dtoResponseArticle = new DTOResponseArticleCre();
        dtoResponseArticle.responseArticle(savedArticle);
        return dtoResponseArticle;
    }

    @Override
    public List<DTOResponseArticle> getAll(Integer pageNo, Integer pageSize, String sortBy, Sort.Direction sortDirection) {
        Sort sort = Sort.by(sortDirection, sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Article> articlePage = articleRepository.findAll(pageable);
        List<Article> articleList = articlePage.getContent();
        List<DTOResponseArticle> dtoResponseArticles = new ArrayList<>();
        for (Article article : articleList) {
            DTOResponseArticle dtoResponseArticle = new DTOResponseArticle();
//            List<Integer> categoryIdList = new ArrayList<>();
//            for (CategoryArticle categoryArticle : article.getCategoryArticles()){
//                categoryIdList.add(categoryArticle.getCategories().getCategoryId());
//            }
            dtoResponseArticle.responseArticleWithCategoryIdList(article);
            dtoResponseArticles.add(dtoResponseArticle);
        }
        return dtoResponseArticles;
    }

    @Override
    public DTOCommentResponse postComment(Long id, DTOCommentRequest commentRequest) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new CustomNotfoundException("Article with id " + id + " does not exist.")
        );

        Comment comment = new Comment();
        comment.setCmt(commentRequest.getComment());
        comment.setArticle(article);
        comment.setUser(article.getUser());

        Comment savedComment = commentRepository.save(comment);

        article.addComment(savedComment);
        articleRepository.save(article);

        DTOResponseArticle dtoResponseArticle = new DTOResponseArticle();
        dtoResponseArticle.responseArticleWithCategoryIdList(article);

        DTOCommentResponse dtoCommentResponse = new DTOCommentResponse();
        dtoCommentResponse.setDtoResponseArticle(dtoResponseArticle);

        dtoCommentResponse.responseComment(article, List.of(savedComment));
        return dtoCommentResponse;
    }

    @Override
    public DTOResponseArticle getArticleById(Long id) {
        Article article = articleRepository.findById(id).orElse(null);
        DTOResponseArticle dtoResponseArticle = new DTOResponseArticle();
        dtoResponseArticle.responseArticleWithCategoryIdList(article);
        return dtoResponseArticle;
    }

    @Override
    public DTOArticleCommentResponse getCommentArticleById(Long id) {
        Article article  = articleRepository.findById(id).orElse(null);
        DTOArticleCommentResponse dtoArticleCommentResponse = new DTOArticleCommentResponse();
        dtoArticleCommentResponse.responseArticleComment(article);
        return dtoArticleCommentResponse;
    }

    @Override
    public Article deleteArticle(Long id) throws Exception {
        if (!getCurrentUser.getCurrentUser().getRole().equals("AUTHOR")) {
            throw new Exception("User reader can not delete article");
        }
        Article article = articleRepository.findById(id).orElse(null);
        if (!Objects.isNull(article)){
            articleRepository.deleteById(id);
        }
        return null;
    }

    @Override
    public DTOArticleCommentResponse update(Long id, DTORequestArticle dtoRequestArticle) throws Exception {
        if (!getCurrentUser.getCurrentUser().getRole().equals("AUTHOR")) {
            throw new Exception("User reader cannot update article");
        }

        Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            throw new Exception("Article not found");
        }

        categoryArticleRepository.deleteAllByArticles(article);

        article.setTitle(dtoRequestArticle.getTitle());
        article.setDescription(dtoRequestArticle.getDescription());

        List<Category> categories = categoryRepository.findAllById(dtoRequestArticle.getCategoryId());
        for (Category category : categories) {
            CategoryArticle categoryArticle = new CategoryArticle();
            categoryArticle.setArticles(article);
            categoryArticle.setCategories(category);
            article.getCategoryArticles().add(categoryArticle);
        }

        Article updatedArticle = articleRepository.save(article);

        DTOArticleCommentResponse dtoArticleCommentResponse = new DTOArticleCommentResponse();
        dtoArticleCommentResponse.responseArticleComment(updatedArticle);

        return dtoArticleCommentResponse;
    }



}
