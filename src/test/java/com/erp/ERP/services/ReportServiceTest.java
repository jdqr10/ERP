package com.erp.ERP.services;

import com.erp.ERP.dto.ReportDto;
import com.erp.ERP.models.*;
import com.erp.ERP.repository.ReportRepository;
import com.erp.ERP.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ReportServiceTest {

    @Mock
    private ReportRepository reportRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ReportService reportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByUserId_returnsReportDtos() {
        // Arrange
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        Client client = new Client();
        client.setId(2L);

        Order order = new Order();
        order.setId(3L);

        Report report = new Report();
        report.setId(4L);
        report.setGenerationDate(LocalDate.now());
        report.setFilePath("report.pdf");
        report.setType("ANUAL");
        report.setClient(client);
        report.setUser(user);
        report.setOrder(order);

        List<Report> reports = List.of(report);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(reportRepository.findByUser(user)).thenReturn(reports);

        // Act
        List<ReportDto> result = reportService.findByUserId(userId);

        // Assert
        assertThat(result).hasSize(1);
        ReportDto dto = result.get(0);
        assertThat(dto.getId()).isEqualTo(4L);
        assertThat(dto.getUserId()).isEqualTo(1L);
        assertThat(dto.getClientId()).isEqualTo(2L);
        assertThat(dto.getOrderId()).isEqualTo(3L);
        assertThat(dto.getFilePath()).isEqualTo("report.pdf");
        assertThat(dto.getType()).isEqualTo("ANUAL");

        verify(userRepository).findById(userId);
        verify(reportRepository).findByUser(user);
    }
}