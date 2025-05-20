package com.erp.ERP.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.ERP.dto.CompanyDto;
import com.erp.ERP.models.Company;
import com.erp.ERP.repository.CompanyRepository;

@Service
public class CompanyService {
    
    @Autowired
    private CompanyRepository companyRepository;

    //Get method to retrieve all companies
    public List<CompanyDto> findAll() {
        List<CompanyDto> companyToReturn = new ArrayList<>();
        List<Company> companies = companyRepository.findAll();

        for (Company company : companies) {
            CompanyDto companyDto = new CompanyDto(company);
            companyToReturn.add(companyDto);
        }

        return companyToReturn;
    }

    //Post method to create a new company
    public CompanyDto createCompany(CompanyDto request) {
        Company company = new Company();
        company.setName(request.getName());

        // save the company to the database
        Company savedCompany = companyRepository.save(company);

        // Convert the saved company to CompanyDto
        return new CompanyDto(savedCompany);
    }

    //Delete method to delete a company
    public String deleteCompany(Long id) {
        if (!companyRepository.existsById(id)) {
            return "Company with id " + id + " does not exist";
        }else{
            companyRepository.deleteById(id);
            return "Company with id " + id + " deleted successfully";
        }
    }

    //Put method to update a company
    public CompanyDto updateCompany(Long id, CompanyDto request) {
       return companyRepository.findById(id)
                .map(company -> {
                    company.setName(request.getName());
                    Company updatedCompany = companyRepository.save(company);
                    return new CompanyDto(updatedCompany);
                })
                .orElseThrow(() -> new RuntimeException("Company with id " + id + " not found"));
    }
}