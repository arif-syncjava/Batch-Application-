package com.arifsyncjava.restfulapi.movie.utils;

import com.arifsyncjava.restfulapi.exception.ErrorMessage;
import com.arifsyncjava.restfulapi.movie.execptions.InvalidArgumentException;
import com.arifsyncjava.restfulapi.movie.request.MovieSortRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class RequestValidator {

    public  void validate (MovieSortRequest request) {

        String year = request.year().trim();
        String sort = request.sort().toLowerCase().trim();
        String top = request.top().trim();

        if (!(year.length() == 4) )
            throw new InvalidArgumentException(ErrorMessage.INVALID_YEAR.getMessage());

        if (!(sort.equals("asc") || sort.equals("desc"))) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_SORT.getMessage());
        }
        if (!StringUtils.hasText(top)) {
             throw new InvalidArgumentException(ErrorMessage.INVALID_TOP.getMessage());
        }

    }







}
