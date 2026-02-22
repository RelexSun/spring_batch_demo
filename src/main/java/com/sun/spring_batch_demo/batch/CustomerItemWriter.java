package com.sun.spring_batch_demo.batch;

import com.sun.spring_batch_demo.datasource.entity.Customer;
import com.sun.spring_batch_demo.datasource.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerItemWriter implements ItemWriter<Customer> {
    private final CustomerRepository repo;


    @Override
    public void write(Chunk<? extends Customer> chunk) {
        repo.saveAll(chunk.getItems());
    }
}
