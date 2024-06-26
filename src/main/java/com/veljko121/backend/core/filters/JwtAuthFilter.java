package com.veljko121.backend.core.filters;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.veljko121.backend.core.service.IJwtService;
import com.veljko121.backend.service.IUserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final IJwtService jwtService;
    private final IUserService userService;
    private final Logger logger;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authenticationHeader = request.getHeader("Authorization");
        if (authenticationHeader != null && authenticationHeader.startsWith("Bearer ")) {
            final String jwt = authenticationHeader.substring(7);
            final String username = jwtService.extractUsername(jwt);
            if (username != null && !isUserAuthenticated()) {
                try {
                    var userDetails = userService.loadUserByUsername(username);
                    if (jwtService.isJwtValid(jwt, userDetails)) {
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(jwt, null, userDetails.getAuthorities());
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                } catch (NoSuchElementException e) {
                    logger.error("User with username '" + username + "' could not be found.");
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private Boolean isUserAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication() != null;
    }
    
}
