package com.nexttech.model;

//import org.decimal4j.util.DoubleRounder;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDate;
import java.util.*;


@Document(collection = "invoice")
public class Invoice {

    @Id
    private String id;
    private int invoiceNumber;
    private Company seller;
    private List<Product> products;
    private double total;
    private LocalDate dueDate;
    private LocalDate payDate;
    private boolean duplicate;

    public Invoice() {
    }

    public Invoice(int invoiceNumber, Company seller, List<Product> products, boolean checkPayDate) {
        this.invoiceNumber = invoiceNumber;
        this.products = products;
        this.seller = seller;

        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }

        this.total = Math.round(total * 100.0) / 100.0;

        Random random = new Random();

        dueDate = LocalDate.now().plusDays(random.nextInt(5));
        if (checkPayDate) {
            payDate = LocalDate.now().minusDays(random.nextInt(5));
        }
    }

    public void setPayDate(LocalDate payDate) {
        this.payDate = payDate;
    }

    public boolean isDuplicate() {
        return duplicate;
    }

    public void setDuplicate(boolean duplicate) {
        this.duplicate = duplicate;
    }

    public Company getSeller() {
        return seller;
    }

    public void setSeller(Company seller) {
        this.seller = seller;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getTotal() {
        return total;
    }


    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getPayDate() {
        return payDate;
    }


    public boolean isPaid() {
        return payDate != null;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Invoice that = (Invoice) obj;
        if (this.isPaid() != that.isPaid()) return false;
        return true;
    }


    @Override
    public int hashCode() {
        return Objects.hash(invoiceNumber);
    }
}
