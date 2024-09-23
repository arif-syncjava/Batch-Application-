package com.arifsyncjava.restfulapi.exception;

import com.arifsyncjava.restfulapi.batch.BatchException;
import com.arifsyncjava.restfulapi.movie.execptions.InvalidArgumentException;
import com.arifsyncjava.restfulapi.movie.execptions.MovieNotFoundException;
import com.arifsyncjava.restfulapi.response.ApiError;
import com.arifsyncjava.restfulapi.response.Error;
import com.arifsyncjava.restfulapi.upload.FileUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler (InvalidArgumentException.class)
    public ResponseEntity<ApiError> handleInvalidArgument (InvalidArgumentException exception) {
        var error = new Error(exception.getHttpStatus(),exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiError(error));
    }

    @ExceptionHandler (FileUploadException.class)
    public ResponseEntity<ApiError> handleFileUploadException (FileUploadException exception) {
        var error = new Error(exception.getHttpStatus(),exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiError(error));
    }

    @ExceptionHandler (BatchException.class)
    public ResponseEntity<ApiError> handleBatchException (BatchException exception) {
        var error = new Error(exception.getHttpStatus(),exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiError(error));
    }

    @ExceptionHandler (MovieNotFoundException.class)
    public ResponseEntity<ApiError> handleBatchException (MovieNotFoundException exception) {
        var error = new Error(exception.getHttpStatus(),exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiError(error));
    }


}
