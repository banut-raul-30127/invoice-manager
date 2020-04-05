package com.nexttech.service;

import com.nexttech.model.Company;
import com.nexttech.model.Invoice;
import com.nexttech.model.Product;
import com.nexttech.repository.CompanyRepository;
import com.nexttech.repository.InvoiceRepository;
import com.nexttech.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class GenerateService {

    private GenerateName generateName = new GenerateName();
    private GeneratePhoneNumber generatePhoneNumber = new GeneratePhoneNumber();
    private Random random = new Random();


    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    /**
     * this method is used to return the companies
     */
    public void generateCompanies() {

        List<String> names = new ArrayList<>();

        int i = 0;
        while (i < 3) {
            String test = generateName.getRandomFirstName() + " " + generateName.getRandomMiddleName() + " " + generateName.getRandomLastName();
            if (!names.contains(test)) {
                names.add(test);
                i++;
            }
        }

        while (i < 24) {
            int numberOfNames = random.nextInt(2);
            String test;
            if (numberOfNames == 0) {
                test = generateName.getRandomFirstName() + " " + generateName.getRandomMiddleName();
            } else {
                test = generateName.getRandomFirstName() + " " + generateName.getRandomMiddleName() + " " + generateName.getRandomLastName();
            }
            if (!names.contains(test)) {
                names.add(test);
                i++;
            }
        }

        List<Company> companies = new ArrayList<>();

        for (i = 0; i < names.size(); i++) {
            Company company = new Company(names.get(i), generatePhoneNumber.getRandomPhoneNumber());
            companies.add(company);
        }

        companyRepository.save(companies);
    }


    /**
     * this method is used to return the products
     */
    public void generateProducts() {

        List<Product> products = new ArrayList<>();
        List<String> names = new ArrayList<>();

        int i = 0;
        while (i < 48) {
            String name = generateName.get5RandomAlphabeticalChar();
            if (!names.contains(name)) {
                names.add(name);
                i++;
            }
        }

        for (i = 0; i < names.size(); i++) {
            Product product = new Product(i, names.get(i), (double) (random.nextInt(999) + 1) / 10);
            products.add(product);
        }

        productRepository.save(products);

    }

    /**
     * this method is used to return the products for the invoices
     */
    public void generateInvoices() {

        List<Company> companies = companyRepository.findAll();
        List<Product> products = productRepository.findAll();
        List<Invoice> invoices = new ArrayList<>();
        Invoice invoice;

        int index = 0;
        int indexToCreateInvoicesWithoutPayDate = 0;
        for (int i = 0; i < 50; i++) {

            int randomNumberOfProducts = random.nextInt(3) + 1;
            List<Product> productsArrayList = new ArrayList<>();

            for (int j = 0; j < randomNumberOfProducts; j++) {
                productsArrayList.add(products.get(random.nextInt(48)));
            }

            if (i >= 24) {
                if (indexToCreateInvoicesWithoutPayDate < 6) {
                    invoice = new Invoice(i, companies.get(random.nextInt(24)), productsArrayList, false);
                    indexToCreateInvoicesWithoutPayDate++;
                } else {
                    invoice = new Invoice(i, companies.get(random.nextInt(24)), productsArrayList, random.nextBoolean());
                }
            } else {
                invoice = new Invoice(i, companies.get(index), productsArrayList, random.nextBoolean());
                index++;
            }
            invoices.add(invoice);
            if (i == 49) {
                Invoice invoice1 = new Invoice(50, invoice.getSeller(), invoice.getProducts(), invoice.isCheckPayDate());
                invoice1.setDueDate(invoice.getDueDate());
                invoice1.setPayDate(invoice.getPayDate());
                invoice1.setDuplicate(true);
                invoices.add(invoice1);
            }
        }

        invoiceRepository.save(invoices);
    }


    public void refreshData(){
        System.out.println("Am sters datele!");
        invoiceRepository.removeAll();
        System.out.println("Am sters datele!");
        generateData();
    }

    @PostConstruct
    public void generateData() {

        generateCompanies();

        generateProducts();

        generateInvoices();

    }

}

