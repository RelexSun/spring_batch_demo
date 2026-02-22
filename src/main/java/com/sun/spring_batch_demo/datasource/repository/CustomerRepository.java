package com.sun.spring_batch_demo.datasource.repository;

import com.sun.spring_batch_demo.datasource.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
