package com.arifsyncjava.restfulapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends RuntimeException{
    private HttpStatus httpStatus;

    public BaseException (HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }



}
