package com.example.nimap.PayrollTask.springboot.Services;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nimap.PayrollTask.springboot.Entities.JobEntity;
import com.example.nimap.PayrollTask.springboot.Repository.CustomerRepository;
import com.springboot.nimap.PayrollTask.springboot.Util.ExcelExportUtils;

@Service
//@AllArgsConstructor
public class CustomerService {
	@Autowired
    private CustomerRepository customerRepository;
    

    public List<JobEntity> exportCustomerToExcel(HttpServletResponse response) throws IOException {
    	System.out.println("123");
        List<JobEntity> jobList = customerRepository.findAll();
        ExcelExportUtils exportUtils = new ExcelExportUtils(jobList);
        exportUtils.exportDataToExcel(response);
        return jobList;
    }

}
