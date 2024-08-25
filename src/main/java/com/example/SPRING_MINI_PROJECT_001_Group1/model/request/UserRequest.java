package com.example.SPRING_MINI_PROJECT_001_Group1.model.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String username;
    private String email;
    private String role= "AUTHOR";
    private String password;
    private String address;
    private String phoneNumber;
}
