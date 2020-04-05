package com.nexttech.repository;

import com.nexttech.model.Product;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final MongoTemplate mongoTemplate;

    public ProductRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void save(List<Product> products) {
        mongoTemplate.insertAll(products);
    }


    @Override
    public List<Product> findAll() {
        return mongoTemplate.findAll(Product.class);
    }


}
