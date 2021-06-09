package com.demo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such user")  // 404
public class UpdatingUserException extends RuntimeException {

    public UpdatingUserException(String message) {
        super(message);
    }
}
