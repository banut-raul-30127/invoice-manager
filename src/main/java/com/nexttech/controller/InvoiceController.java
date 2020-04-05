package com.nexttech.controller;

import com.nexttech.DTO.InvoiceGenerateDTO;
import com.nexttech.model.Invoice;
import com.nexttech.service.GenerateService;
import com.nexttech.service.InvoiceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InvoiceController {


    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private GenerateService generateService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showAll(Model model) {
        model.addAttribute("invoices", invoiceService.findAll());
        return "index";
    }


    @RequestMapping(value = "/search-by-text", method = RequestMethod.GET)
    public String findByText(Model model, @RequestParam(value = "text") String text) {
        if (StringUtils.isBlank(text)) {
            model.addAttribute("invoices", invoiceService.findAll());
            return "index";
        }
        model.addAttribute("invoices", invoiceService.find(text));
        return "invoicesByText";
    }

    @RequestMapping(value = "/refresh-data", method = RequestMethod.POST)
    public String refresh(Model model, InvoiceGenerateDTO invoiceGenerateDTO) {
        generateService.refreshData(invoiceGenerateDTO);

        model.addAttribute("invoices", invoiceService.findAll());
        return "index";
    }
}
