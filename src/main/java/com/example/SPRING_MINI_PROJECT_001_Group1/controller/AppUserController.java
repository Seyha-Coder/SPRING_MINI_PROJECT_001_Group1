package com.example.SPRING_MINI_PROJECT_001_Group1.controller;

import com.example.SPRING_MINI_PROJECT_001_Group1.model.ApiResponse;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.AuthResponse;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.AppUserDto;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.request.AuthRequest;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.request.UserRequest;
import com.example.SPRING_MINI_PROJECT_001_Group1.security.JwtService;
import com.example.SPRING_MINI_PROJECT_001_Group1.service.AppUserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AppUserController {

    private final AppUserService appUserService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public AppUserController(AppUserService appUserService, JwtService jwtService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.appUserService = appUserService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRequest userRequest) {
        AppUserDto appUserDto = appUserService.register(userRequest);

        ApiResponse<AppUserDto> apiResponse = ApiResponse.<AppUserDto>builder()
                .payload(appUserDto)
                .message("Register successfully.")
                .code(201)
                .status(HttpStatus.CREATED)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest authRequest) throws Exception {
        AppUserDto appUser = appUserService.findUserByusername(authRequest.getUsername().toLowerCase());

        if (appUser == null) {
            throw new Exception("User not found");
        }

        authenticate(appUser.getUsername(), authRequest.getPassword());

        final UserDetails userDetails = appUserService.loadUserByUsername(appUser.getUsername());
        final String jwt = jwtService.generateToken(userDetails);

        AuthResponse authResponse = new AuthResponse(jwt);

        ApiResponse<AppUserDto> apiResponse = ApiResponse.<AppUserDto>builder()
                .payload(appUser)
                .message("Login successfully")
                .token(authResponse.getToken())
                .code(200)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(apiResponse);
    }


    private void authenticate(String username, String password) throws Exception {
        try {
            UserDetails userApp = appUserService.loadUserByUsername(username);
            if (userApp == null) {
                throw new Exception("Wrong Email");
            }
            if (!passwordEncoder.matches(password, userApp.getPassword())) {
                throw new Exception("Wrong Password");
            }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
    @GetMapping
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
}
