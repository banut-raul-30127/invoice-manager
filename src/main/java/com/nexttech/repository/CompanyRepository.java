package com.nexttech.repository;

import com.nexttech.model.Company;

import java.util.List;

public interface CompanyRepository {

    void save(List<Company> companies);

    List<Company> findAll();
}
