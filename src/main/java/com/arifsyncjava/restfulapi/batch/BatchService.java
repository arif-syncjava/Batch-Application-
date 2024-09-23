package com.arifsyncjava.restfulapi.batch;

import com.arifsyncjava.restfulapi.exception.ErrorMessage;
import com.arifsyncjava.restfulapi.response.SimpleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BatchService {

    private final JobLauncher jobLauncher;
    private final Job job;

    public ResponseEntity<SimpleResponse> execute () {
        try {
            jobLauncher.run(job, new JobParametersBuilder()
                    .addLong("starAt", System.currentTimeMillis())
                    .toJobParameters());
        } catch (JobExecutionAlreadyRunningException
                 | JobRestartException
                 | JobInstanceAlreadyCompleteException
                 | JobParametersInvalidException exception)
        {
            throw new BatchException(ErrorMessage.BATCH_OPERATION_FAIL.getMessage());
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SimpleResponse("Batch operation successful"));

    }



}
