package com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.dtoArticle.dtoArticleReponse;

import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOArticleCommentResponse {
    private DTOResponseArticle dtoResponseArticle;
    private List<CommentDTO> commentList;
    public void responseArticleComment(Article article) {
        DTOResponseArticle dtoResponseArticle = new DTOResponseArticle();
        dtoResponseArticle.responseArticleWithCategoryIdList(article);
        this.dtoResponseArticle = dtoResponseArticle;

        List<CommentDTO> commentDTOList = article.getComments().stream()
                .map(comment -> {

                    CommentDTO commentDTO = new CommentDTO();
                    commentDTO.setCommentId(comment.getId());
                    commentDTO.setComment(comment.getCmt());
                    commentDTO.setCreatedAt(comment.getCreatedAt());
                    commentDTO.setUpdatedAt(comment.getUpdatedAt());

                    // User DTO
                    UserDTO userDTO = new UserDTO();
                    userDTO.setUserId(comment.getUser().getId());
                    userDTO.setUsername(comment.getUser().getUsername());
                    userDTO.setEmail(comment.getUser().getEmail());
                    userDTO.setAddress(comment.getUser().getAddress());
                    userDTO.setPhoneNumber(comment.getUser().getPhoneNumber());
                    userDTO.setCreatedAt(comment.getUser().getCreatedAt());
                    userDTO.setRole(comment.getUser().getRole());

                    commentDTO.setUser(userDTO);

                    return commentDTO;
                })
                .collect(Collectors.toList());

        this.commentList = commentDTOList;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CommentDTO {
        private Long commentId;
        private String comment;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private UserDTO user;


    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserDTO {
        private Long userId;
        private String username;
        private String email;
        private String address;
        private String phoneNumber;
        private LocalDateTime createdAt;
        private String role;
    }



}
