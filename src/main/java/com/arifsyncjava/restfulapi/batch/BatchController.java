package com.arifsyncjava.restfulapi.batch;

import com.arifsyncjava.restfulapi.response.SimpleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BatchController {

    private final BatchService batchService;

    @GetMapping(path = "/start")
    public ResponseEntity<SimpleResponse>  batchStart () {
        return batchService.execute();
    }


}
