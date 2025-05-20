package com.erp.ERP.services;

import com.erp.ERP.dto.CompanyDto;
import com.erp.ERP.models.Company;
import com.erp.ERP.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CompanyServiceTest {

    @InjectMocks
    private CompanyService companyService;

    @Mock
    private CompanyRepository companyRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllCompanies() {
        // Arrange
        Company company1 = new Company();
        company1.setId(1L);
        company1.setName("Empresa A");

        Company company2 = new Company();
        company2.setId(2L);
        company2.setName("Empresa B");

        List<Company> companyList = Arrays.asList(company1, company2);
        when(companyRepository.findAll()).thenReturn(companyList);

        // Act
        List<CompanyDto> result = companyService.findAll();

        // Assert
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("Empresa A");
        assertThat(result.get(1).getName()).isEqualTo("Empresa B");

        verify(companyRepository, times(1)).findAll();
    }
}
