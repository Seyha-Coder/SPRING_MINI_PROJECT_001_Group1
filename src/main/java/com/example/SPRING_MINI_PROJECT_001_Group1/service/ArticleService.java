package com.example.SPRING_MINI_PROJECT_001_Group1.service;

import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.AppUserDto;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.dtoArticle.dtoArticleReponse.DTOArticleCommentResponse;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.dtoArticle.dtoArticleReponse.DTOResponseArticle;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.dtoArticle.dtoArticleReponse.DTOResponseArticleCre;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.dtoArticle.dtoArticleRequest.DTORequestArticle;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.dtoComment.dtoCommentRequest.DTOCommentRequest;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.dtoComment.dtoCommentResponse.DTOCommentResponse;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Article;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ArticleService {
    DTOResponseArticleCre postArticle(DTORequestArticle dtoRequestArticle, AppUserDto currentUser) throws Exception;

    List<DTOResponseArticle> getAll(Integer pageNo, Integer pageSize, String sortBy, Sort.Direction sortDirection);

    DTOCommentResponse postComment(Long id, DTOCommentRequest dtoCommentRequest);

    DTOResponseArticle getArticleById(Long id);

    DTOArticleCommentResponse getCommentArticleById(Long id);

    Article deleteArticle(Long id) throws Exception;

    DTOArticleCommentResponse update(Long id, DTORequestArticle dtoRequestArticle) throws Exception;

}
