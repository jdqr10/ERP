package com.erp.ERP.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.erp.ERP.dto.ReportDto;
import com.erp.ERP.services.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // Get all reports
    @GetMapping
    public List<ReportDto> getAllReports() {
        return reportService.findAll();
    }

    // Get reports by user (only one method for this)
    @GetMapping("/user/{userId}")
    public List<ReportDto> getReportsByUserId(@PathVariable Long userId) {
        return reportService.findByUserId(userId);
    }

    // Get reports of type "sales", ADMIN and EMPLOYEE roles
    @GetMapping("/sales")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public List<ReportDto> getSalesReports() {
        return reportService.getReportsByType("sales");
    }

    // Obtain "inventory" type reports, ADMIN and EMPLOYEE roles
    @GetMapping("/inventory")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public List<ReportDto> getInventoryReports() {
        return reportService.getReportsByType("inventory");
    }

    // Obtain "trends" type reports, ADMIN role only
    @GetMapping("/trends")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ReportDto> getTrendsReports() {
        return reportService.getReportsByType("trends");
    }

    // Create a new report
    @PostMapping("/create")
    public ReportDto createReport(@RequestBody ReportDto reportDto) {
        return reportService.createReport(reportDto);
    }

    // Delete a report by ID
    @DeleteMapping("/delete/{id}")
    public String deleteReport(@PathVariable Long id) {
        return reportService.deleteReport(id);
    }
}
