package com.arifsyncjava.restfulapi.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    INVALID_YEAR ("Enter a valid year"),
    YEAR_NOT_AVAILABLE("Year not available on the web server"),
    INVALID_SORT ("Sort argument is not valid"),
    INVALID_TOP ("Enter a valid top argument"),
    FILE_UPLOAD_FAIL("File upload failed.Try again"),
    BATCH_OPERATION_FAIL("Batch processing fail"),
    MOVIE_NOT_FOUND("The movie not available on the web server"),
    BATCH_READER_CONFIGURATION_FAIL("Batch reader configuration fail"),
    BATCH_READING_PROCESS_FAIL("Batch reading process fail"),
    BATCH_WRITING_PROCESS_FAIL("Batch writing process fail"),
    BATCH_WRITER_CONFIGURATION_FAIL("Batch writer configuration fail"),
    BATCH_DATA_TRANSFER_FAIL("Batch data transfer fail");


    private final String message;


}
