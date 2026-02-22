package com.sun.spring_batch_demo.config;

import com.sun.spring_batch_demo.dto.CustomerRecord;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.batch.infrastructure.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class BatchConfig {

    @Bean
    @StepScope
    public FlatFileItemReader<CustomerRecord> reader() {
        return new FlatFileItemReaderBuilder<CustomerRecord>()
                .name("customerItemReader")
                .resource(new ClassPathResource("data/customers.csv"))
                .delimited()
                .names("id", "firstName", "lastName", "email", "gender", "contactNo", "country", "dob")
                .targetType(CustomerRecord.class)
                .linesToSkip(1)
                .build();
    }
}
