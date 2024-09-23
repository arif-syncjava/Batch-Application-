package com.arifsyncjava.restfulapi.upload;

import com.arifsyncjava.restfulapi.response.SimpleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @PostMapping(path = "/upload")
    public ResponseEntity<SimpleResponse> upload (@RequestParam("file")MultipartFile file) {
        return fileUploadService.execute(file);
    }




}
