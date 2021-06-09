package com.demo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Offer")  // 404
public class OrderNotFoundException extends RuntimeException {
    // ...

    public OrderNotFoundException(String message) {
        super(message);
    }
}
