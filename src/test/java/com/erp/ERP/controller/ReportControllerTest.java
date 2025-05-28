package com.erp.ERP.controller;

import com.erp.ERP.dto.ReportDto;
import com.erp.ERP.services.ReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ReportControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ReportService reportService;

    @InjectMocks
    private ReportController reportController;

    private ReportDto reportDto1;
    private ReportDto reportDto2;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reportController).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        reportDto1 = new ReportDto();
        reportDto1.setId(1L);
        reportDto1.setGenerationDate(LocalDate.of(2024, 5, 1));
        reportDto1.setFilePath("/files/report1.pdf");
        reportDto1.setType("Factura");
        reportDto1.setClientId(100L);
        reportDto1.setUserId(10L);
        reportDto1.setOrderId(200L);

        reportDto2 = new ReportDto();
        reportDto2.setId(2L);
        reportDto2.setGenerationDate(LocalDate.of(2024, 5, 2));
        reportDto2.setFilePath("/files/report2.pdf");
        reportDto2.setType("Resumen");
        reportDto2.setClientId(101L);
        reportDto2.setUserId(10L);
        reportDto2.setOrderId(201L);
    }

    @Test
    void getAllReports_ShouldReturnAllReports() throws Exception {
        List<ReportDto> reports = Arrays.asList(reportDto1, reportDto2);
        when(reportService.findAll()).thenReturn(reports);

        mockMvc.perform(get("/api/reports"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));
    }

    @Test
    void getReportsByUserId_ShouldReturnReportsForUser() throws Exception {
        List<ReportDto> reports = Arrays.asList(reportDto1);
        when(reportService.findByUserId(10L)).thenReturn(reports);

        mockMvc.perform(get("/api/reports/user/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].userId").value(10L));
    }

    @Test
    void createReport_ShouldCreateAndReturnReport() throws Exception {
        when(reportService.createReport(any(ReportDto.class))).thenReturn(reportDto1);

        mockMvc.perform(post("/api/reports/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reportDto1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.filePath").value("/files/report1.pdf"));
    }

    @Test
    void deleteReport_ShouldReturnSuccessMessage() throws Exception {
        when(reportService.deleteReport(1L)).thenReturn("Reporte con id 1 eliminado correctamente");

        mockMvc.perform(delete("/api/reports/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Reporte con id 1 eliminado correctamente"));
    }
}
