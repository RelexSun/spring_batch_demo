package com.sun.spring_batch_demo.batch;

import com.sun.spring_batch_demo.datasource.entity.Customer;
import com.sun.spring_batch_demo.datasource.repository.CustomerRepository;
import com.sun.spring_batch_demo.dto.CustomerRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.parameters.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class CustomerImportJobConfig {
    private final CustomerItemProcessor customerItemProcessor;
    private final CustomerItemWriter customerItemWriter;
    private final CustomerJobCompletionListener listener;

    @Bean
    public Job customerImportJob(JobRepository jobRepository, Step userImportStep) {
        return new JobBuilder("customerImportJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(userImportStep)
                .listener(listener)
                .build();
    }

    @Bean
    public Step customerImportStep(JobRepository jobRepository, FlatFileItemReader<CustomerRecord> csvUserReader) {
        return new StepBuilder("customerImportStep", jobRepository)
                .<CustomerRecord, Customer>chunk(10)
                .reader(csvUserReader)
                .processor(customerItemProcessor)
                .writer(customerItemWriter)
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public SimpleAsyncTaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(10);
        return asyncTaskExecutor;
    }
}
