package com.raul.controller;

import com.raul.DTO.InvoiceGenerateDTO;
import com.raul.service.GenerateService;
import com.raul.service.InvoiceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@AllArgsConstructor
public class InvoiceController {

    private InvoiceService invoiceService;
    private GenerateService generateService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showAll(Model model) {
        model.addAttribute("invoices", invoiceService.findAll());
        log.info("search");
        return "index";
    }

    /**
     * this method is used to search a company by a company name
     * @param model sent by frontend
     * @param text is the company name
     * @return the new page that contains the company
     */
    @RequestMapping(value = "/search-by-text", method = RequestMethod.GET)
    public String findByText(Model model, @RequestParam(value = "text") String text) {
        if (StringUtils.isBlank(text)) {
            model.addAttribute("invoices", invoiceService.findAll());
            return "index";
        }
        model.addAttribute("invoices", invoiceService.find(text));
        return "invoicesByText";
    }

    /**
     * this method is used to refresh-data in mongoDB
     * @param model sent by frontend
     * @param invoiceGenerateDTO is the submit for a new set of data
     * @return the new page with the data
     */
    @RequestMapping(value = "/refresh-data", method = RequestMethod.POST)
    public String refresh(Model model, InvoiceGenerateDTO invoiceGenerateDTO) {
        generateService.refreshData(invoiceGenerateDTO);

        model.addAttribute("invoices", invoiceService.findAll());
        return "index";
    }

    /**
     * this method is used for the payment check
     * @param model sent by frontend
     * @param invoiceNumber is specific for the pay
     * @return the page with all invoices after you paid one
     */
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public String refresh(Model model, @RequestParam(value = "invoiceNumber") Integer invoiceNumber) {
        invoiceService.pay(invoiceNumber);

        model.addAttribute("invoices", invoiceService.findAll());
        return "index";
    }
}
