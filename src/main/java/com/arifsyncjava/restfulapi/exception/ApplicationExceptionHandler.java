package com.arifsyncjava.restfulapi.exception;

import com.arifsyncjava.restfulapi.batch.BatchException;
import com.arifsyncjava.restfulapi.movie.execptions.InvalidArgumentException;
import com.arifsyncjava.restfulapi.movie.execptions.MovieNotFoundException;
import com.arifsyncjava.restfulapi.response.Error;
import com.arifsyncjava.restfulapi.response.Response;
import com.arifsyncjava.restfulapi.upload.FileUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler (InvalidArgumentException.class)
    public ResponseEntity<Response> handleInvalidArgument (InvalidArgumentException exception) {
        var error = new Error(exception.getHttpStatus(),exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Response.error(error));
    }

    @ExceptionHandler (FileUploadException.class)
    public ResponseEntity<Response> handleFileUploadException (FileUploadException exception) {
        var error = new Error(exception.getHttpStatus(),exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.error(error));
    }

    @ExceptionHandler (BatchException.class)
    public ResponseEntity<Response> handleBatchException (BatchException exception) {
        var error = new Error(exception.getHttpStatus(),exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Response.error(error));
    }

    @ExceptionHandler (MovieNotFoundException.class)
    public ResponseEntity<Response> handleBatchException (MovieNotFoundException exception) {
        var error = new Error(exception.getHttpStatus(),exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Response.error(error));
    }


}
