package com.example.SPRING_MINI_PROJECT_001_Group1.service.serviceImpl;

import com.example.SPRING_MINI_PROJECT_001_Group1.config.GetCurrentUser;
import com.example.SPRING_MINI_PROJECT_001_Group1.exception.CustomNotfoundException;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.dto.AppUserDto;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.entity.User;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.request.UserRequest;
import com.example.SPRING_MINI_PROJECT_001_Group1.repository.AppUserRepository;
import com.example.SPRING_MINI_PROJECT_001_Group1.service.AppUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final GetCurrentUser getCurrentUser;

    public AppUserServiceImpl(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, GetCurrentUser getCurrentUser) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.getCurrentUser = getCurrentUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    @Override
    public AppUserDto register(UserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAddress(request.getAddress());
        user.setPhoneNumber(request.getPhoneNumber());
        user = appUserRepository.save(user);

        return modelMapper.map(user, AppUserDto.class);
    }

    @Override
    public AppUserDto findUserByEmail(String email) {
        User appUser = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new CustomNotfoundException("User not found with email: " + email));
        return modelMapper.map(appUser, AppUserDto.class);
    }

    @Override
    public AppUserDto findUserByusername(String username) {
        User appUser = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new CustomNotfoundException("User not found with name: " + username));
        return modelMapper.map(appUser, AppUserDto.class);
    }

    @Override
    public AppUserDto viewUserDetails() {
        User currentUser = getCurrentUser.getCurrentUser();
        return modelMapper.map(currentUser, AppUserDto.class);
    }

    @Override
    public AppUserDto updateUser(UserRequest userRequest) {
        User currentUser = getCurrentUser.getCurrentUser();

        currentUser.setUsername(userRequest.getUsername());
        currentUser.setEmail(userRequest.getEmail());
        currentUser.setRole(userRequest.getRole());
        currentUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        currentUser.setAddress(userRequest.getAddress());
        currentUser.setPhoneNumber(userRequest.getPhoneNumber());

        User updatedUser = appUserRepository.save(currentUser);
        return modelMapper.map(updatedUser, AppUserDto.class);
    }




}
