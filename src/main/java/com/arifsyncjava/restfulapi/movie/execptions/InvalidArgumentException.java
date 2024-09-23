package com.arifsyncjava.restfulapi.movie.execptions;

import com.arifsyncjava.restfulapi.exception.BaseException;
import org.springframework.http.HttpStatus;

public class InvalidArgumentException extends BaseException {
    public InvalidArgumentException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

}
