package com.nexttech.repository;

import com.nexttech.model.Product;

import java.util.List;

public interface ProductRepository {

    void save(List<Product> products);

    List<Product> findAll();
}
