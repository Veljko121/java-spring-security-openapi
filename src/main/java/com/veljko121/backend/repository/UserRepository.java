package com.veljko121.backend.repository;

import java.util.Optional;

import com.veljko121.backend.core.repository.GenericEntityRepository;
import com.veljko121.backend.model.User;

public interface UserRepository extends GenericEntityRepository<User> {
    
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
