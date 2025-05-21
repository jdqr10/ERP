package com.erp.ERP.controller;

import com.erp.ERP.dto.CompanyDto;
import com.erp.ERP.services.CompanyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CompanyController.class)
@AutoConfigureMockMvc(addFilters = false) // Disable security filters for testing
public class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllCompanies() throws Exception {
        CompanyDto company1 = new CompanyDto();
        company1.setName("Company A");

        CompanyDto company2 = new CompanyDto();
        company2.setName("Company B");

        List<CompanyDto> mockList = Arrays.asList(company1, company2);

        when(companyService.findAll()).thenReturn(mockList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/companies/getAllCompanies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(mockList.size()))
                .andExpect(jsonPath("$[0].name").value("Company A"))
                .andExpect(jsonPath("$[1].name").value("Company B"))
                .andDo(print());
    }

    @Test
    public void testCreateCompany() throws Exception {
        CompanyDto request = new CompanyDto();
        request.setName("New Company");

        CompanyDto response = new CompanyDto();
        response.setName("New Company");

        when(companyService.createCompany(any(CompanyDto.class))).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/companies/createCompany")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Company"))
                .andDo(print());
    }


}
