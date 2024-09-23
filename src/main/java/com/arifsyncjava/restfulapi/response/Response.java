package com.arifsyncjava.restfulapi.response;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public record Response(
        String time,
        Map<String,?> data) {

    public Response(Map<String,?> data) {
        this(getTime(), data);
    }


    private static String getTime() {
        long timeStamp = System.currentTimeMillis();
        ZonedDateTime now = ZonedDateTime.ofInstant(Instant.ofEpochMilli(timeStamp),
                ZoneId.of("UTC"));
        return now.format(DateTimeFormatter.ofPattern("hh:mm:ss-EEEE,dd MMMM,yyyy"));
    }



}

