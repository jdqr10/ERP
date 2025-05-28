//package com.erp.ERP.services;
//
//import com.erp.ERP.dto.ReportDto;
//import com.erp.ERP.models.Report;
//import com.erp.ERP.repository.ReportRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class ReportServiceTest {
//
//    @Mock
//    private ReportRepository reportRepository;
//
//    @InjectMocks
//    private ReportService reportService;
//
//    private Report report1;
//    private Report report2;
//    private ReportDto reportDto1;
//
//    @BeforeEach
//    void setUp() {
//        // Setup report1
//        report1 = new Report();
//        report1.setId(1L);
//        report1.setGenerationDate(LocalDate.of(2024, 5, 1));
//        report1.setFilePath("/files/report1.pdf");
//        report1.setType("Factura");
//        report1.setId(10L);
//        report1.setId(200L);
//        report1.setClient(100L);
//
//        // Setup reportDto1
//        reportDto1 = new ReportDto();
//        reportDto1.setFilePath("/files/report1.pdf");
//        reportDto1.setGenerationDate(LocalDate.of(2024, 5, 1));
//        reportDto1.setType("Factura");
//        reportDto1.setClientId(100L);
//        reportDto1.setUserId(10L);
//        reportDto1.setOrderId(200L);
//    }
//
//    @Test
//    void findAll_ShouldReturnAllReports() {
//        // Given
//        when(reportRepository.findAll()).thenReturn(Arrays.asList(report1, report2));
//
//        // When
//        List<ReportDto> result = reportService.findAll();
//
//        // Then
//        assertEquals(2, result.size());
//        verify(reportRepository, times(1)).findAll();
//    }
//
//    @Test
//    void findByUserId_ShouldReturnUserReports() {
//        // Given
//        when(reportRepository.findByUserId(10L)).thenReturn(Arrays.asList(report1));
//
//        // When
//        List<ReportDto> result = reportService.findByUserId(10L);
//
//        // Then
//        assertEquals(1, result.size());
//        assertEquals(10L, result.get(0).getUserId());
//        verify(reportRepository, times(1)).findByUserId(10L);
//    }
//
//    @Test
//    void createReport_ShouldSaveAndReturnReportDto() {
//        // Given
//        when(reportRepository.save(any(Report.class))).thenReturn(report1);
//
//        // When
//        ReportDto result = (ReportDto) reportService.createReport(reportDto1);
//
//        // Then
//        assertNotNull(result);
//        assertEquals("/files/report1.pdf", result.getFilePath());
//        assertEquals("Factura", result.getType());
//        assertEquals(100L, result.getClientId());
//        verify(reportRepository, times(1)).save(any(Report.class));
//    }
//
//    @Test
//    void deleteReport_WhenExists_ShouldDeleteAndReturnSuccessMessage() {
//        // Given
//        Long reportId = 1L;
//        when(reportRepository.existsById(reportId)).thenReturn(true);
//
//        // When
//        String result = (String) reportService.deleteReport(reportId);
//
//        // Then
//        assertEquals("Reporte con id 1 eliminado correctamente", result);
//        verify(reportRepository, times(1)).deleteById(reportId);
//    }
//
//    @Test
//    void deleteReport_WhenNotExists_ShouldReturnNotFoundMessage() {
//        // Given
//        Long reportId = 99L;
//        when(reportRepository.existsById(reportId)).thenReturn(false);
//
//        // When
//        String result = (String) reportService.deleteReport(reportId);
//
//        // Then
//        assertEquals("Reporte con id 99 no existe", result);
//        verify(reportRepository, never()).deleteById(reportId);
//    }
//
//    // Additional test for findById if needed
//    @Test
//    void findById_WhenExists_ShouldReturnReportDto() {
//        // Given
//        Long reportId = 1L;
//        when(reportRepository.findById(reportId)).thenReturn(Optional.of(report1));
//
//        // When
//        Optional<ReportDto> result = reportService.findById(reportId);
//
//        // Then
//        assertTrue(result.isPresent());
//        assertEquals(reportId, result.get().getId());
//        verify(reportRepository, times(1)).findById(reportId);
//    }
//
//    @Test
//    void findById_WhenNotExists_ShouldReturnEmpty() {
//        // Given
//        Long reportId = 99L;
//        when(reportRepository.findById(reportId)).thenReturn(Optional.empty());
//
//        // When
//        Optional<ReportDto> result = reportService.findById(reportId);
//
//        // Then
//        assertFalse(result.isPresent());
//        verify(reportRepository, times(1)).findById(reportId);
//    }
//}