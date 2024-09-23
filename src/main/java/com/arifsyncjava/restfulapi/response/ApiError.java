package com.arifsyncjava.restfulapi.response;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public record ApiError(String time, Error error) {

    public ApiError(Error error) {
        this(getTime(), error);
    }


    private static String getTime() {
        long timeStamp = System.currentTimeMillis();
        ZonedDateTime now = ZonedDateTime.ofInstant(Instant.ofEpochMilli(timeStamp),
                ZoneId.of("UTC"));
        return now.format(DateTimeFormatter.ofPattern("hh:mm:ss-dd MMMM,yyyy"));
    }




}
