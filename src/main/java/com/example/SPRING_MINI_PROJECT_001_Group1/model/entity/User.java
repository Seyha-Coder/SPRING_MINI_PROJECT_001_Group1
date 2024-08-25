package com.example.SPRING_MINI_PROJECT_001_Group1.model.entity;

import com.example.SPRING_MINI_PROJECT_001_Group1.model.enums.UserRoleEnums;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user_tb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "username")
    private String username;
    private String email;
    private String role;
    private String password;
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "created_at",nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();
}
