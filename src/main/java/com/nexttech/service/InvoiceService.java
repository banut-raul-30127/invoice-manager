package com.nexttech.service;


import com.nexttech.model.Invoice;

import java.util.List;

public interface InvoiceService {

    List<Invoice> find(String text);

    List<Invoice> findAll();

}
