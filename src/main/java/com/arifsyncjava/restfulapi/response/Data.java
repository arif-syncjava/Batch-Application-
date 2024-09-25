package com.arifsyncjava.restfulapi.response;

import org.springframework.http.HttpStatus;

import java.util.Map;

public record Data (int code,
                    String status,
                    String message,
                    Map<String,?>data) {
    public Data (HttpStatus httpStatus, String message,Map<String,?>data) {
        this(
                httpStatus.value(),
                httpStatus.getReasonPhrase(),
                message,
                data);
    }
}
