package com.arifsyncjava.restfulapi.movie.service;

import com.arifsyncjava.restfulapi.Query;
import com.arifsyncjava.restfulapi.exception.ErrorMessage;
import com.arifsyncjava.restfulapi.movie.execptions.InvalidArgumentException;
import com.arifsyncjava.restfulapi.movie.utils.RequestValidator;
import com.arifsyncjava.restfulapi.response.Response;
import com.arifsyncjava.restfulapi.movie.persistence.MovieRepository;
import com.arifsyncjava.restfulapi.movie.request.MovieSortRequest;
import com.arifsyncjava.restfulapi.movie.response.MovieSortResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class MovieSortService implements Query<MovieSortRequest, Response> {

    private final MovieRepository movieRepository;
    private final RequestValidator validator;

    @Override
    public ResponseEntity<Response> execute(MovieSortRequest request) {
        validator.validate(request);
        List<MovieSortResponse> response = movieRepository.sortMovie(request);
        if (response.isEmpty())
            throw new InvalidArgumentException(ErrorMessage.YEAR_NOT_AVAILABLE.getMessage());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new Response(Map.of("movies", response)));
    }
}
