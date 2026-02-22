package com.sun.spring_batch_demo.dto;

public record CustomerRecord(
        Long id,
        String firstName,
        String lastName,
        String email,
        String gender,
        String contactNo,
        String country,
        String dob
) {}
