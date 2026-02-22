package com.sun.spring_batch_demo.batch;

import com.sun.spring_batch_demo.datasource.entity.Customer;
import com.sun.spring_batch_demo.dto.CustomerRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomerItemProcessor implements ItemProcessor<CustomerRecord, Customer> {

    @Override
    public Customer process(CustomerRecord item) {

        // set validation as needed (optional)
        if (!item.email().contains("@")) {
            log.warn("Skipping record with invalid email: {}", item);
            return null;
        }

        return Customer.builder()
                .id(item.id())
                .firstName(item.firstName())
                .lastName(item.lastName())
                .country(item.country())
                .dob(item.dob())
                .email(item.email())
                .gender(item.gender())
                .contactNo(item.contactNo())
                .build();
    }
}
