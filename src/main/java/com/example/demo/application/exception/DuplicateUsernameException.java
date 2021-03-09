package com.example.demo.application.exception;

import static java.text.MessageFormat.format;

public class DuplicateUsernameException extends RuntimeException {
    private static final long serialVersionUID = 3082188725660020101L;

    public DuplicateUsernameException(String username) {
        super(format("User name [{0}] exists", username));
    }
}
