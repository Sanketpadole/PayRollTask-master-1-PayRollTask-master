package com.example.nimap.PayrollTask.springboot.Controllers;


import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.nimap.PayrollTask.springboot.Services.CustomerService;

@Controller
@RequestMapping("/customers")
//@AllArgsConstructor
public class CustomerController {
	@Autowired
    private CustomerService customerService;

    @GetMapping("/export-to-excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Customers_Information.xlsx";
        response.setHeader(headerKey, headerValue);
        customerService.exportCustomerToExcel(response);

    }
}
