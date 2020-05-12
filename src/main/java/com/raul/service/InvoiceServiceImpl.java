package com.raul.service;

import com.raul.model.Invoice;
import com.raul.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private InvoiceRepository invoiceRepository;

    @Override
    public List<Invoice> find(String text) {
        return invoiceRepository.findByText(text);
    }

    @Override
    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public void pay(Integer invoiceNumber) {
        invoiceRepository.pay(invoiceNumber);
    }
}