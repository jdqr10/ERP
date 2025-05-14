package com.erp.ERP.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.ERP.dto.CompanyDto;
import com.erp.ERP.models.Company;
import com.erp.ERP.repository.CompanyRepository;

@Service
public class CompanyService {
    
    @Autowired
    private CompanyRepository companyRepository;

    //Post method to create a new company
    public CompanyDto createCompany(CompanyDto request) {
        Company company = new Company();
        company.setName(request.getName());

        // save the company to the database
        Company savedCompany = companyRepository.save(company);

        // Convert the saved company to CompanyDto
        return new CompanyDto(savedCompany);
    }
}
