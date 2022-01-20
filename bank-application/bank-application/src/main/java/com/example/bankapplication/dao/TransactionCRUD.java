package com.example.bankapplication.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface TransactionCRUD extends CrudRepository<Transaction, Integer> {
    Iterable<Transaction> findByFromAccountNumber(Long fromAccountNumber);
    Iterable<Transaction> findByToAccountNumber(Long toAccountNumber);
    Iterable<Transaction> findByTransactionDateBetween(Date fromDate, Date endDate);
}
