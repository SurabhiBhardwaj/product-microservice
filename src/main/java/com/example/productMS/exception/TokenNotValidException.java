package com.example.productMS.exception;

import org.omg.SendingContext.RunTime;

public class TokenNotValidException extends RuntimeException {

    private String message;

    public TokenNotValidException() {
    }

    public TokenNotValidException(String msg) {
        super(msg);
        this.message = msg;
    }
}
