package com.erp.ERP.services;

import com.erp.ERP.dto.ReportDto;
import com.erp.ERP.models.Client;
import com.erp.ERP.models.Order;
import com.erp.ERP.models.Report;
import com.erp.ERP.models.User;
import com.erp.ERP.repository.ClientRepository;
import com.erp.ERP.repository.OrderRepository;
import com.erp.ERP.repository.ReportRepository;
import com.erp.ERP.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {

    @Mock private ReportRepository reportRepository;
    @Mock private UserRepository userRepository;
    @Mock private ClientRepository clientRepository;
    @Mock private OrderRepository orderRepository;

    @InjectMocks
    private ReportService reportService;

    private Report report1;
    private Report report2;
    private ReportDto reportDto1;

    @BeforeEach
    void setUp() {
        User user = new User(); user.setId(10L);
        Client client = new Client(); client.setId(100L);
        Order order = new Order(); order.setId(200L);

        report1 = new Report();
        report1.setId(1L);
        report1.setGenerationDate(LocalDate.of(2024, 5, 1));
        report1.setFilePath("/files/report1.pdf");
        report1.setType("Invoice");
        report1.setUser(user);
        report1.setClient(client);
        report1.setOrder(order);

        report2 = new Report();
        report2.setId(2L);
        report2.setGenerationDate(LocalDate.of(2024, 5, 2));
        report2.setFilePath("/files/report2.pdf");
        report2.setType("Receipt");

        reportDto1 = new ReportDto();
        reportDto1.setFilePath("/files/report1.pdf");
        reportDto1.setGenerationDate(LocalDate.of(2024, 5, 1));
        reportDto1.setType("Invoice");
        reportDto1.setClientId(100L);
        reportDto1.setUserId(10L);
        reportDto1.setOrderId(200L);
    }

    @Test
    void findAll_ShouldReturnAllReports() {
        when(reportRepository.findAll()).thenReturn(Arrays.asList(report1, report2));

        List<ReportDto> result = reportService.findAll();

        assertEquals(2, result.size());
        verify(reportRepository, times(1)).findAll();
    }

    @Test
    void findByUserId_ShouldReturnReportsForUser() {
        when(userRepository.findById(10L)).thenReturn(Optional.of(report1.getUser()));
        when(reportRepository.findByUser(report1.getUser())).thenReturn(List.of(report1));

        List<ReportDto> result = reportService.findByUserId(10L);

        assertEquals(1, result.size());
        assertEquals(10L, result.get(0).getUserId());
        verify(reportRepository, times(1)).findByUser(report1.getUser());
    }

    @Test
    void createReport_ShouldSaveAndReturnReportDto() {
        when(clientRepository.findById(100L)).thenReturn(Optional.of(report1.getClient()));
        when(userRepository.findById(10L)).thenReturn(Optional.of(report1.getUser()));
        when(orderRepository.findById(200L)).thenReturn(Optional.of(report1.getOrder()));
        when(reportRepository.save(any(Report.class))).thenReturn(report1);

        ReportDto result = reportService.createReport(reportDto1);

        assertNotNull(result);
        assertEquals("Invoice", result.getType());
        verify(reportRepository).save(any(Report.class));
    }

    @Test
    void deleteReport_WhenExists_ShouldDeleteAndReturnSuccessMessage() {
        when(reportRepository.existsById(1L)).thenReturn(true);

        String result = reportService.deleteReport(1L);

        assertEquals("Report with id 1 deleted successfully", result);
        verify(reportRepository).deleteById(1L);
    }

    @Test
    void deleteReport_WhenNotExists_ShouldReturnNotFoundMessage() {
        when(reportRepository.existsById(99L)).thenReturn(false);

        String result = reportService.deleteReport(99L);

        assertEquals("Report with id 99 does not exist", result);
        verify(reportRepository, never()).deleteById(99L);
    }

    @Test
    void findById_WhenExists_ShouldReturnReportDto() {
        when(reportRepository.findById(1L)).thenReturn(Optional.of(report1));

        Optional<ReportDto> result = reportService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void findById_WhenNotExists_ShouldReturnEmptyOptional() {
        when(reportRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<ReportDto> result = reportService.findById(99L);

        assertFalse(result.isPresent());
    }

    @Test
    void getReportsByType_ShouldReturnMatchingReports() {
        when(reportRepository.findByType("Invoice")).thenReturn(List.of(report1));

        List<ReportDto> result = reportService.getReportsByType("Invoice");

        assertEquals(1, result.size());
        assertEquals("Invoice", result.get(0).getType());
        verify(reportRepository).findByType("Invoice");
    }
}
