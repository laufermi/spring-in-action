package com.example.demo.application.exception;

import static java.text.MessageFormat.format;

public class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -9092157641176053751L;

    public UserNotFoundException(String username) {
        super(format("Cannot found user by name [{0}]", username));
    }
}
