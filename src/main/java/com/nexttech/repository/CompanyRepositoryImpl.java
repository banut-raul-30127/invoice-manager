package com.nexttech.repository;

import com.nexttech.model.Company;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyRepositoryImpl implements CompanyRepository {

    private final MongoTemplate mongoTemplate;

    public CompanyRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void save(List<Company> companies) {
        mongoTemplate.insertAll(companies);
    }

    @Override
    public List<Company> findAll() {
        return mongoTemplate.findAll(Company.class);
    }
}
