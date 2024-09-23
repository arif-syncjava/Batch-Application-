package com.arifsyncjava.restfulapi.movie.model;

public record Movie(
        String movieCode,
        String name,
        String year,
        String category) { }
