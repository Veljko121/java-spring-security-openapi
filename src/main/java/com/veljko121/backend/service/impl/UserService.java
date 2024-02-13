package com.veljko121.backend.service.impl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.veljko121.backend.core.exception.EmailNotUniqueException;
import com.veljko121.backend.core.exception.UsernameNotUniqueException;
import com.veljko121.backend.model.User;
import com.veljko121.backend.repository.UserRepository;
import com.veljko121.backend.service.IUserService;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByUsername(String username) throws NoSuchElementException {
        return userRepository.findByUsername(username).orElseThrow();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return findByUsername(username);
    }

    @Override
    public User save(User user) {
        if (!isUsernameUnique(user)) throw new UsernameNotUniqueException(user.getUsername());
        if (!isEmailUnique(user)) throw new EmailNotUniqueException(user.getEmail());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    private Boolean isUsernameUnique(User user) {
        return isUsernameUnique(user.getUsername());
    }

    private Boolean isEmailUnique(User user) {
        return isEmailUnique(user.getEmail());
    }

    private Boolean isUsernameUnique(String username) {
        return !userRepository.findAll().stream().filter(u -> u.getUsername().equals(username)).findAny().isPresent();
    }

    private Boolean isEmailUnique(String email) {
        return !userRepository.findAll().stream().filter(u -> u.getEmail().equals(email)).findAny().isPresent();
    }
    
}
