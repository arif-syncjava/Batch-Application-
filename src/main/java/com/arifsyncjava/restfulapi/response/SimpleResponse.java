package com.arifsyncjava.restfulapi.response;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public record SimpleResponse(
        String time,
        String message) {

    public SimpleResponse(String message) {
        this(getTime(), message);

    }

    private static String getTime() {
        long timeStamp = System.currentTimeMillis();
        ZonedDateTime now = ZonedDateTime.ofInstant(Instant.ofEpochMilli(timeStamp),
                ZoneId.of("UTC"));
        return now.format(DateTimeFormatter.ofPattern("ss:mm:hh-dd/MM/yyyy"));
    }

}
