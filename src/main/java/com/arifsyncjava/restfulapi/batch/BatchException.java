package com.arifsyncjava.restfulapi.batch;

import com.arifsyncjava.restfulapi.exception.BaseException;
import org.springframework.http.HttpStatus;

public class BatchException extends BaseException {
    public BatchException (String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}
