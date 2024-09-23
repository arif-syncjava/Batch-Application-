package com.arifsyncjava.restfulapi.upload;

import com.arifsyncjava.restfulapi.exception.BaseException;
import com.arifsyncjava.restfulapi.exception.ErrorMessage;
import org.springframework.http.HttpStatus;

public class FileUploadException extends BaseException {
    public FileUploadException () {
        super( HttpStatus.INTERNAL_SERVER_ERROR,
                ErrorMessage.FILE_UPLOAD_FAIL.getMessage());
    }
}
