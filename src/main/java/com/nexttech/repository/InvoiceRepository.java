package com.nexttech.repository;

import com.nexttech.model.Invoice;

import java.util.List;

public interface InvoiceRepository {

    void save(List<Invoice> invoices);

    List<Invoice> findAll();

    List<Invoice> findByText(String text);

    void removeAll();
}
