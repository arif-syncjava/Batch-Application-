package com.arifsyncjava.restfulapi.movie.persistence;

import com.arifsyncjava.restfulapi.movie.request.MovieSearchRequest;
import com.arifsyncjava.restfulapi.movie.request.MovieSortRequest;
import com.arifsyncjava.restfulapi.movie.response.MovieResponse;
import com.arifsyncjava.restfulapi.movie.response.MovieSortResponse;

import java.util.List;
import java.util.Optional;

public interface MovieRepository {

    List<MovieSortResponse> sortMovie (MovieSortRequest request);
    Optional<MovieResponse> searchMovieByName (MovieSearchRequest request);



}
