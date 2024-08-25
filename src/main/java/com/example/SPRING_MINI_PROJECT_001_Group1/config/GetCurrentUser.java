package com.example.SPRING_MINI_PROJECT_001_Group1.config;

import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.User;
import com.example.SPRING_MINI_PROJECT_001_Group1.repository.AppUserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class GetCurrentUser {

    private final AppUserRepository appUserRepository;

    public GetCurrentUser(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return appUserRepository.findByUsername(userDetails.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found with username: " + userDetails.getUsername()));
        }
        throw new RuntimeException("No authentication found");
    }
}
