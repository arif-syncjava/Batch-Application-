package com.arifsyncjava.restfulapi.response;

import org.springframework.http.HttpStatus;

public record Error (int code, String status, String message) {
    public Error (HttpStatus httpStatus, String errorMessage) {
        this(
                httpStatus.value(),
                httpStatus.getReasonPhrase(),
                errorMessage);
    }
}
