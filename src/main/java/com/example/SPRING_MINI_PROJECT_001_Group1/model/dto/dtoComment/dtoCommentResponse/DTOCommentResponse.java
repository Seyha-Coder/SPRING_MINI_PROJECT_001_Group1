package com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.dtoComment.dtoCommentResponse;

import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.dtoArticle.dtoArticleReponse.DTOResponseArticle;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Article;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.Comment;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DTOCommentResponse {
    private DTOResponseArticle dtoResponseArticle;
    private List<CommentDTO> commentList = new ArrayList<>();

    public void responseComment(Article article, List<Comment> comments) {
        this.dtoResponseArticle = new DTOResponseArticle();
        this.dtoResponseArticle.responseArticleWithCategoryIdList(article);

        for (Comment comment : comments) {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.dtoComment(comment);

            User user = comment.getUser();
            UserDTO userDTO = new UserDTO();
            userDTO.dtoUser(user);

            commentDTO.setUser(userDTO);

            this.commentList.add(commentDTO);
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class CommentDTO {
        private Long commentId;
        private String comment;
        private LocalDateTime createdAt;
        private UserDTO user;

        public void dtoComment(Comment comments) {
            commentId = comments.getId();
            comment = comments.getCmt();
            createdAt = comments.getCreatedAt();
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class UserDTO {
        private Long userId;
        private String username;
        private String email;
        private String address;
        private String phoneNumber;
        private LocalDateTime createdAt;
        private String role;

        public void dtoUser(User user){
            userId = user.getId();
            username = user.getUsername();
            email = user.getEmail();
            address = user.getAddress();
            phoneNumber = user.getPhoneNumber();
            createdAt = user.getCreatedAt();
            role = user.getRole();
        }
    }
}
