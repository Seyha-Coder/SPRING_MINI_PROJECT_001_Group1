package com.example.SPRING_MINI_PROJECT_001_Group1.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseCategory <T> {
    private String message;
    private HttpStatus status;
    private T payload;
}
