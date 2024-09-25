package com.arifsyncjava.restfulapi.movie.controller;

import com.arifsyncjava.restfulapi.movie.request.MovieSearchRequest;
import com.arifsyncjava.restfulapi.movie.request.MovieSortRequest;
import com.arifsyncjava.restfulapi.movie.service.MovieSortService;
import com.arifsyncjava.restfulapi.movie.service.SearchMovieByNameService;
import com.arifsyncjava.restfulapi.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MovieController {

    private final MovieSortService movieSortService;
    private final SearchMovieByNameService searchMovieByNameService;

    @PostMapping(path = "/search")
    public ResponseEntity<Response> searchMovie (
            @RequestBody MovieSearchRequest request) {
        return searchMovieByNameService.execute(request);
    }

    @PostMapping
    public ResponseEntity<Response> readSortedMovie (
            @RequestBody MovieSortRequest request) {
        return movieSortService.execute(request);
    }





}
