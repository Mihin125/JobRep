package com.demo.Exceptions;

public class UpdatingPasswordException extends RuntimeException{

    public UpdatingPasswordException(String message) {
        super(message);
    }
}
