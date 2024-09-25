package com.arifsyncjava.restfulapi.response;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Response {
    private String time;
    private Data data;
    private Error error;

    private Response () {
        this.time = getTime();
    }

    private Response (Data data) {
        this();
        this.data = data;
    }

    private Response (Error error) {
        this();
        this.error = error;
    }


    public static Response of (Data data) {
        return new Response(data);
    }

    public static Response error (Error error) {
        return new Response(error);
    }

    private static String getTime () {
        long timeStamp = System.currentTimeMillis();
        ZonedDateTime now = ZonedDateTime
                .ofInstant(Instant.ofEpochMilli(timeStamp), ZoneId.of("UTC"));
        return now.format(DateTimeFormatter.ISO_DATE_TIME);
    }

}
