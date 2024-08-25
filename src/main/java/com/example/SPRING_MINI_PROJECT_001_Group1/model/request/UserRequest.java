package com.example.SPRING_MINI_PROJECT_001_Group1.model.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Address is required")
    private String address;
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+\\d{1,3} \\d{1,4}\\)\\d{1,4}-\\d{1,4}$", message = "Phone number must be in the format +XX XXXX)XXXX-XXXX")
    private String phoneNumber;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must be at least 8 characters long, include at least 1 uppercase letter, 1 digit, and 1 special character")
    private String password;
    @NotBlank(message = "Role is required")
    @Size(max = 50, message = "Role length should not exceed 50 characters")
    private String role = "AUTHOR";
}
