package com.veljko121.backend.dto;

import java.time.LocalDateTime;

import com.veljko121.backend.core.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

@Data
public class UserResponseDTO {
    
    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotEmpty
    private Role role;

    @PastOrPresent
    private LocalDateTime createdDateTime;

}
