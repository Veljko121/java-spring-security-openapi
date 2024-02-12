package com.veljko121.backend.service.impl;

import java.time.LocalDateTime;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.veljko121.backend.core.enums.Role;
import com.veljko121.backend.dto.CredentialsDTO;
import com.veljko121.backend.model.User;
import com.veljko121.backend.service.IAuthenticationService;
import com.veljko121.backend.service.IUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {

    private final IUserService userService;

    private final AuthenticationManager authenticationManager;

    @Override
    public User register(User user) throws DuplicateKeyException {
        user.setCreatedDateTime(LocalDateTime.now());
        user.setRole(Role.USER);
        return userService.save(user);
    }

    @Override
    public User login(CredentialsDTO credentialsDTO) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(credentialsDTO.getUsername(), credentialsDTO.getPassword())
        );
        return userService.findByUsername(credentialsDTO.getUsername());
    }
    
}
