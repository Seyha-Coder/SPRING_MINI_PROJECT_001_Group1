package com.example.SPRING_MINI_PROJECT_001_Group1.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse <T>{
    String message;
    HttpStatus status;
    int code;
    T payload;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String token;
}
