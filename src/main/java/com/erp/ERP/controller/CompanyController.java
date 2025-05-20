package com.erp.ERP.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.erp.ERP.services.CompanyService;
import com.erp.ERP.dto.CompanyDto;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    
    @Autowired
    private CompanyService companyService;


    //Get all companies
    @GetMapping("/getAllCompanies")
    public List<CompanyDto> getAllCompanies() {
        return companyService.findAll();
    }

    //Save a new company
    @PostMapping("/createCompany")
    public CompanyDto createCompany(@RequestBody CompanyDto request) {
        return companyService.createCompany(request);
    }

    //update an existing company
    @PutMapping("updateCompany/{id}")
    public CompanyDto updateCompany(@PathVariable Long id, @RequestBody CompanyDto request) {
        return companyService.updateCompany(id, request);
    }

    //Delete a company by its id
    @DeleteMapping("/deleteCompany/{id}")
    public String deleteCompany(@PathVariable Long id) {
        return companyService.deleteCompany(id);
    }
}
