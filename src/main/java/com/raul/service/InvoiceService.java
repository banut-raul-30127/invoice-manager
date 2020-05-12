package com.raul.service;

import com.raul.model.Invoice;
import com.raul.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InvoiceService {

    List<Invoice> find(String text);

    List<Invoice> findAll();

    void pay(Integer invoiceNumber);
}
