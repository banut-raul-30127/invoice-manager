package com.raul.DTO;

public class InvoiceGenerateDTO {

    private String companyNames;
    private Integer numberCompanies;
    private Integer numberProducts;
    private Integer numberInvoices;

    public String getCompanyNames() {
        return companyNames;
    }

    public void setCompanyNames(String companyNames) {
        this.companyNames = companyNames;
    }

    public Integer getNumberCompanies() {
        return numberCompanies;
    }

    public void setNumberCompanies(Integer numberCompanies) {
        this.numberCompanies = numberCompanies;
    }

    public Integer getNumberProducts() {
        return numberProducts;
    }

    public void setNumberProducts(Integer numberProducts) {
        this.numberProducts = numberProducts;
    }

    public Integer getNumberInvoices() {
        return numberInvoices;
    }

    public void setNumberInvoices(Integer numberInvoices) {
        this.numberInvoices = numberInvoices;
    }
}
