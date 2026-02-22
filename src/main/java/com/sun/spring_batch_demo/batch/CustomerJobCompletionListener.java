package com.sun.spring_batch_demo.batch;

import com.sun.spring_batch_demo.datasource.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomerJobCompletionListener implements JobExecutionListener {

    private final CustomerRepository repo;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("Job started: {}", jobExecution.getJobInstance().getJobName());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("Job finished with status: {}", jobExecution.getStatus());
        log.info("Total users in DB: {}", repo.count());
    }

}
