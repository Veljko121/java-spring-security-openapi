package com.veljko121.backend.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegisterRequestDTO {
    
    @NotEmpty
    private String username;

    @NotEmpty
    private String email;
    
    @NotEmpty
    private String password;

}
