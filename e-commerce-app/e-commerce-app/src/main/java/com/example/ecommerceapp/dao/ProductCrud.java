package com.example.ecommerceapp.dao;

import org.springframework.data.repository.CrudRepository;

public interface ProductCrud extends CrudRepository<Product, Integer> {
    Iterable<Product> findByCategory(String category);
}
