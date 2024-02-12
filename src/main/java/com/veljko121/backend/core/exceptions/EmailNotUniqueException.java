package com.veljko121.backend.core.exceptions;

public class EmailNotUniqueException extends AttributeNotUniqueException {

    public EmailNotUniqueException(String email) {
        super("email", email);
    }
    
}
