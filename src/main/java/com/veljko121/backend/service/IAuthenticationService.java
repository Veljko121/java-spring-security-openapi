package com.veljko121.backend.service;

import org.springframework.dao.DuplicateKeyException;

import com.veljko121.backend.dto.CredentialsDTO;
import com.veljko121.backend.model.User;

public interface IAuthenticationService {
    
    User register(User user) throws DuplicateKeyException;

    User login(CredentialsDTO credentialsDTO);

}
