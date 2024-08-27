package com.example.SPRING_MINI_PROJECT_001_Group1.model.response;

import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.AppUserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    String token;
}
