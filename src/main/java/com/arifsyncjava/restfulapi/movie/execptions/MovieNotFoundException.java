package com.arifsyncjava.restfulapi.movie.execptions;

import com.arifsyncjava.restfulapi.exception.BaseException;
import com.arifsyncjava.restfulapi.exception.ErrorMessage;
import org.springframework.http.HttpStatus;

public class MovieNotFoundException extends BaseException {
    public MovieNotFoundException() {
        super(HttpStatus.NOT_FOUND, ErrorMessage.MOVIE_NOT_FOUND.getMessage());
    }

}
