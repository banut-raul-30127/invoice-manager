package com.nexttech.service;


import com.nexttech.model.Invoice;
import com.nexttech.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {


    @Autowired
    private InvoiceRepository invoiceRepository;


    @Override
    public List<Invoice> find(String text) {
        return invoiceRepository.findByText(text);
    }

    @Override
    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

}
