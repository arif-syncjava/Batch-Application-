package com.arifsyncjava.restfulapi.movie.service;

import com.arifsyncjava.restfulapi.Query;
import com.arifsyncjava.restfulapi.movie.execptions.MovieNotFoundException;
import com.arifsyncjava.restfulapi.movie.persistence.MovieRepository;
import com.arifsyncjava.restfulapi.movie.request.MovieSearchRequest;
import com.arifsyncjava.restfulapi.movie.response.MovieResponse;
import com.arifsyncjava.restfulapi.response.Data;
import com.arifsyncjava.restfulapi.response.Response;
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

         var data = new Data(HttpStatus.OK,
                 "Request Successful",
                 Map.of("movie", response));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Response.of(data));
    }

}
