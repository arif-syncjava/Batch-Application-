package com.arifsyncjava.restfulapi.batch;

import com.arifsyncjava.restfulapi.exception.ErrorMessage;
import com.arifsyncjava.restfulapi.movie.model.Movie;
import com.arifsyncjava.restfulapi.movie.model.MovieParse;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class MovieItemProcessor implements ItemProcessor<MovieParse,Movie> {

    @Override
    public Movie process(MovieParse movie) {
        try {
            String name = movie.name();
            int indexOf = name.indexOf("(");
            String movieName = name.substring(0,indexOf).trim();

            String year = name.substring(indexOf);
            year = year.replace("(","");
            year = year.replace(")","");

            String category = movie.category();
            if (category.isEmpty()) category = "general";
            category = category.replace("|",",").trim();

            return new Movie(movie.movieCode(),movieName,year,category);

        } catch (Exception exception) {
            throw new BatchException(ErrorMessage.BATCH_DATA_TRANSFER_FAIL.getMessage());
        }


    }



}
