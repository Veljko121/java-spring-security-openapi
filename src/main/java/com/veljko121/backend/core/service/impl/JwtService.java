package com.veljko121.backend.core.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.veljko121.backend.core.service.IJwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService implements IJwtService {

    @Value("${application.jwt.secret-key}") private String SECRET_KEY; 
    @Value("${application.jwt.expiration-minutes}") private Integer expirationMinutes = 60;
    private Integer expirationMilliseconds = expirationMinutes * 60 * 1000;

    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    public <T> T extractClaim(String jwt, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwt);
        return claimsResolver.apply(claims);
    }

    public String generateJwt(UserDetails userDetails) {
        return generateJwt(new HashMap<>(), userDetails);
    }

    public String generateJwt(Map<String, Object> extraClaims, UserDetails userDetails) {
        var currentTimeMilliseconds = System.currentTimeMillis();
        return Jwts
            .builder()
            .claims(extraClaims)
            .subject(userDetails.getUsername())
            .issuedAt(new Date(currentTimeMilliseconds))
            .expiration(new Date(currentTimeMilliseconds + expirationMilliseconds))
            .signWith(getSignInKey())
            .compact();
    }

    public Boolean isJwtValid(String jwt, UserDetails userDetails) {
        final String username = extractUsername(jwt);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(jwt);
    }

    private Boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }

    private Date extractExpiration(String jwt) {
        return extractClaim(jwt, Claims::getExpiration);
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts
            .parser()
            .verifyWith(getSignInKey())
            .build()
            .parseSignedClaims(jwt)
            .getPayload();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        var key = Keys.hmacShaKeyFor(keyBytes);
        return key;
    }
    
}
