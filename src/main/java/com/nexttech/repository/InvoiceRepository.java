package com.nexttech.repository;

import com.nexttech.model.Invoice;

import java.util.List;

public interface InvoiceRepository {

    void save(List<Invoice> invoices);

    void pay(Integer invoiceNumber);

    List<Invoice> findAll();

    List<Invoice> findByText(String text);

    void removeAll();
}
