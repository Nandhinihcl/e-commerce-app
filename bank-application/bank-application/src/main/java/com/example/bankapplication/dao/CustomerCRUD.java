package com.example.bankapplication.dao;

import org.springframework.data.repository.CrudRepository;

public interface CustomerCRUD extends CrudRepository<Customer, Integer> {
    Customer findByAccountNumber(Long accountNumber);
}
