package com.demo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such User")  // 404
public class CredentialsException extends RuntimeException {

    public CredentialsException(String message) {
        super(message);
    }
}
