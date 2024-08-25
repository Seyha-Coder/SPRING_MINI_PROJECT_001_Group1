package com.example.SPRING_MINI_PROJECT_001_Group1.service;

import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.AppUserDto;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.request.UserRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends UserDetailsService {
    AppUserDto register(UserRequest request);
    AppUserDto findUserByEmail(String email);
}
