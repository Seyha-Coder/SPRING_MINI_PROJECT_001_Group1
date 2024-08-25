package com.example.SPRING_MINI_PROJECT_001_Group1.config;

import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class GetCurrentUser {
    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
