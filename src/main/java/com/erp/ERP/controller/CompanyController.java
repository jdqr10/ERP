package com.erp.ERP.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.erp.ERP.services.CompanyService;
import com.erp.ERP.dto.CompanyDto;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    
    @Autowired
    private CompanyService companyService;


    //Save a new company
    @PostMapping("/createCompany")
    public CompanyDto createCompany(@RequestBody CompanyDto request) {
        return companyService.createCompany(request);
    }
}
