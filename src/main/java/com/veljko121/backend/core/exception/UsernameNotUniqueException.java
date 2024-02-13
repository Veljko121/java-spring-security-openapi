package com.veljko121.backend.core.exceptions;

public class UsernameNotUniqueException extends AttributeNotUniqueException {

    public UsernameNotUniqueException(String username) {
        super("username", username);
    }
    
}
