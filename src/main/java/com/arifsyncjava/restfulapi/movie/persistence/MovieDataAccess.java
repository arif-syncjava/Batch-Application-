package com.arifsyncjava.restfulapi.movie.persistence;

import com.arifsyncjava.restfulapi.movie.request.MovieSearchRequest;
import com.arifsyncjava.restfulapi.movie.request.MovieSortRequest;
import com.arifsyncjava.restfulapi.movie.response.MovieResponse;
import com.arifsyncjava.restfulapi.movie.response.MovieSortResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
@Transactional
public class MovieDataAccess implements MovieRepository{

    private final JdbcClient jdbc;
    private final String SEARCH_QUERY =
            "SELECT name,year,category FROM movies WHERE name = :name";


    @Override
    public List<MovieSortResponse> sortMovie(MovieSortRequest request) {
        String year = request.year();
        String sort  = request.sort();
        Integer top = Integer.parseInt(request.top());

        String SORT_QUERY = "SELECT name,category FROM movies WHERE year = :year ORDER BY name " +sort+" LIMIT "+ top;

        return jdbc.sql(SORT_QUERY)
                .param("year", year)
                //.param("sort",sort)
               // .param("top", top)
                .query(MovieSortResponse.class)
                .list();

    }

    @Override
    public Optional<MovieResponse> searchMovieByName(MovieSearchRequest request) {
        String name = request.name();
        return jdbc.sql(SEARCH_QUERY)
                .param("name",name)
                .query(MovieResponse.class)
                .optional();
    }


}
