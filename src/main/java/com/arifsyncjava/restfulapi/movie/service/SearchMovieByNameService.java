package com.arifsyncjava.restfulapi.movie.service;

import com.arifsyncjava.restfulapi.Query;
import com.arifsyncjava.restfulapi.response.Response;
import com.arifsyncjava.restfulapi.movie.execptions.MovieNotFoundException;
import com.arifsyncjava.restfulapi.movie.persistence.MovieRepository;
import com.arifsyncjava.restfulapi.movie.request.MovieSearchRequest;
import com.arifsyncjava.restfulapi.movie.response.MovieResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class SearchMovieByNameService implements Query<MovieSearchRequest, Response> {

    private final MovieRepository movieRepository;

    @Override
    public ResponseEntity<Response> execute(MovieSearchRequest request) {
         MovieResponse response =
                movieRepository.searchMovieByName(request)
                        .orElseThrow(MovieNotFoundException::new);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new Response(Map.of("movie", response)));
    }

}
