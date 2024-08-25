package com.example.SPRING_MINI_PROJECT_001_Group1.controller;

import com.example.SPRING_MINI_PROJECT_001_Group1.model.ApiResponse;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.AppUserDto;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.request.UserRequest;
import com.example.SPRING_MINI_PROJECT_001_Group1.service.AppUserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    private final AppUserService appUserService;

    public UserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }
    @GetMapping("/userDetails")
    public ResponseEntity<?> viewUserDetails(){
        AppUserDto appUserDto = appUserService.viewUserDetails();
        ApiResponse<AppUserDto> apiResponse = ApiResponse.<AppUserDto>builder()
                .payload(appUserDto)
                .message("View user successfully.")
                .code(201)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateCurrentUser(@RequestBody UserRequest userRequest) {
        AppUserDto updatedUser = appUserService.updateUser(userRequest);
        ApiResponse<AppUserDto> apiResponse = ApiResponse.<AppUserDto>builder()
                .payload(updatedUser)
                .message("User updated successfully.")
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
