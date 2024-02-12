package com.veljko121.backend.core.service;

import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;

public interface IJwtService {

    String extractUsername(String jwt);
    <T> T extractClaim(String jwt, Function<Claims, T> claimsResolver);
    String generateJwt(UserDetails userDetails);
    public String generateJwt(Map<String, Object> extraClaims, UserDetails userDetails);
    public Boolean isJwtValid(String jwt, UserDetails userDetails);
    
}
