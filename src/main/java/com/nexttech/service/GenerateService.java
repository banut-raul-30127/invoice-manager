package com.nexttech.service;

import com.nexttech.DTO.InvoiceGenerateDTO;
import com.nexttech.model.Company;
import com.nexttech.model.Invoice;
import com.nexttech.model.Product;
import com.nexttech.repository.InvoiceRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class GenerateService {

    private GenerateName generateName = new GenerateName();
    private GeneratePhoneNumber generatePhoneNumber = new GeneratePhoneNumber();
    private Random random = new Random();

    @Autowired
    private InvoiceRepository invoiceRepository;

    @PostConstruct
    public void generateData() {
        generateData(new InvoiceGenerateDTO());
    }

    public void generateData(InvoiceGenerateDTO invoiceGenerateDTO) {

        List<Company> companies = generateCompanies(invoiceGenerateDTO);
        List<Product> products = generateProducts(invoiceGenerateDTO.getNumberProducts());
        List<Invoice> invoices = generateInvoices(companies, products, invoiceGenerateDTO.getNumberInvoices());

        invoiceRepository.save(invoices);
    }

    public List<Invoice> generateInvoices(List<Company> companies, List<Product> products, Integer numberOfInvoices) {

        List<Invoice> invoices = new ArrayList<>();
        int index = 0;
        int indexToCreateInvoicesWithoutPayDate = 0;

        if (numberOfInvoices == null || numberOfInvoices == 0) {
            numberOfInvoices = 50;
        }

        for (int i = 0; i < numberOfInvoices; i++) {
            Invoice invoice;

            int randomNumberOfProducts = random.nextInt(3) + 1;
            List<Product> productsArrayList = new ArrayList<>();

            for (int j = 0; j < randomNumberOfProducts; j++) {
                productsArrayList.add(products.get(random.nextInt(products.size())));
            }

            int companySize = companies.size();
            if (i >= companySize) {
                if (indexToCreateInvoicesWithoutPayDate < 6) {
                    invoice = new Invoice(i, companies.get(random.nextInt(companySize)), productsArrayList, false);
                    indexToCreateInvoicesWithoutPayDate++;
                } else {
                    invoice = new Invoice(i, companies.get(random.nextInt(companySize)), productsArrayList, random.nextBoolean());
                }
            } else {
                invoice = new Invoice(i, companies.get(index), productsArrayList, random.nextBoolean());
                index++;
            }
            invoices.add(invoice);

        }

        Invoice invoice = invoices.get(invoices.size() - 1);
        Invoice duplicateInvoice = new Invoice(5542, invoice.getSeller(), invoice.getProducts(), invoice.isPaid());
        duplicateInvoice.setDueDate(invoice.getDueDate());
        duplicateInvoice.setPayDate(invoice.getPayDate());
        duplicateInvoice.setDuplicate(true);
        invoices.add(duplicateInvoice);

        return invoices;
    }

    private List<Company> generateCompanies(InvoiceGenerateDTO invoiceGenerateDTO) {

        Set<String> companyNames = validate(invoiceGenerateDTO.getCompanyNames());

        Integer numberOfCompanies = invoiceGenerateDTO.getNumberCompanies();
        if (numberOfCompanies == null || numberOfCompanies == 0) {
            numberOfCompanies = 24;
        }

        if (CollectionUtils.isEmpty(companyNames)) {
            int i = 0;
            while (i < 3) {
                String test = generateName.getRandomFirstName() + " " + generateName.getRandomMiddleName() + " " + generateName.getRandomLastName();
                if (!companyNames.contains(test)) {
                    companyNames.add(test);
                    i++;
                }
            }
            while (i < numberOfCompanies) {
                int numberOfNames = random.nextInt(2);
                String test;
                if (numberOfNames == 0) {
                    test = generateName.getRandomFirstName() + " " + generateName.getRandomMiddleName();
                } else {
                    test = generateName.getRandomFirstName() + " " + generateName.getRandomMiddleName() + " " + generateName.getRandomLastName();
                }
                if (!companyNames.contains(test)) {
                    companyNames.add(test);
                    i++;
                }
            }
        }


        List<Company> companies = new ArrayList<>();


        for (String companyName : companyNames) {
            Company company = new Company(companyName, generatePhoneNumber.getRandomPhoneNumber());
            companies.add(company);
        }

        companies = companies.subList(0, numberOfCompanies);

        return companies;
    }

    private List<Product> generateProducts(Integer numberOfProducts) {

        List<Product> products = new ArrayList<>();
        List<String> names = new ArrayList<>();
        if (numberOfProducts == null || numberOfProducts == 0) {
            numberOfProducts = 48;
        }
        int i = 0;
        while (i < numberOfProducts) {
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
        return products;
    }

    public void refreshData(InvoiceGenerateDTO invoiceGenerateDTO) {
        invoiceRepository.removeAll();
        generateData(invoiceGenerateDTO);
    }

    public Set<String> validate(String companyNames) {
        Set<String> finalList = new HashSet<>();
        if (StringUtils.isBlank(companyNames)) {
            return finalList;
        }
        for (String companyName : companyNames.split(",")) {
            if (StringUtils.isNotBlank(companyName)) {
                finalList.add(companyName.trim());
            }
        }
        return finalList;
    }

}

