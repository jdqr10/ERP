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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<ReportDto> findAll() {
        return reportRepository.findAll().stream()
                .map(this::safeConvertToDto)
                .collect(Collectors.toList());
    }

    public List<ReportDto> findByUserId(Long userId) {
        return userRepository.findById(userId)
                .map(user -> reportRepository.findByUser(user).stream()
                        .map(this::safeConvertToDto)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    public ReportDto createReport(ReportDto dto) {
        Report report = new Report();
        report.setGenerationDate(LocalDate.now());
        report.setFilePath(dto.getFilePath());
        report.setType(dto.getType());

        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + dto.getClientId()));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + dto.getUserId()));
        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + dto.getOrderId()));

        report.setClient(client);
        report.setUser(user);
        report.setOrder(order);

        return safeConvertToDto(reportRepository.save(report));
    }

    public String deleteReport(Long id) {
        if (reportRepository.existsById(id)) {
            reportRepository.deleteById(id);
            return "Report with id " + id + " deleted successfully";
        } else {
            return "Report with id " + id + " does not exist";
        }
    }

    public Optional<ReportDto> findById(Long id) {
        return reportRepository.findById(id)
                .map(this::safeConvertToDto);
    }

    public List<ReportDto> getReportsByType(String type) {
        return reportRepository.findByType(type).stream()
                .map(this::safeConvertToDto)
                .collect(Collectors.toList());
    }

    private ReportDto safeConvertToDto(Report report) {
        Long clientId = report.getClient() != null ? report.getClient().getId() : null;
        Long userId = report.getUser() != null ? report.getUser().getId() : null;
        Long orderId = report.getOrder() != null ? report.getOrder().getId() : null;

        return new ReportDto(
                report.getId(),
                report.getGenerationDate(),
                report.getFilePath(),
                report.getType(),
                clientId,
                userId,
                orderId);
    }
}
