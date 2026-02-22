package com.sun.spring_batch_demo.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch")
@RequiredArgsConstructor
@Slf4j
public class BatchController {

    private final JobOperator jobOperator;
    private final Job customerImportJob;

    @PostMapping("/run")
    @SneakyThrows
    public ResponseEntity<String> runJob(
            @RequestParam(defaultValue = "customers.csv") String file) {

        JobParameters params = new JobParametersBuilder()
                .addString("input.file", file)
                .addLong("run.id", System.currentTimeMillis())
                .toJobParameters();

        // The start method can potentially throw exception
        JobExecution execution = jobOperator.start(customerImportJob, params);

        return ResponseEntity.ok("Job started with status: " + execution.getStatus());
    }
}
