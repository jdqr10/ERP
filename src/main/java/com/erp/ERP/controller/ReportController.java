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

    // Obtener todos los reportes
    @GetMapping
    public List<ReportDto> getAllReports() {
        return reportService.findAll();
    }

    // Obtener reportes por usuario (solo un método para esto)
    @GetMapping("/user/{userId}")
    public List<ReportDto> getReportsByUserId(@PathVariable Long userId) {
        return reportService.findByUserId(userId);
    }

    // Obtener reportes de tipo "sales", roles ADMIN y EMPLOYEE
    @GetMapping("/sales")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public List<ReportDto> getSalesReports() {
        return reportService.getReportsByType("sales");
    }

    // Obtener reportes de tipo "inventory", roles ADMIN y EMPLOYEE
    @GetMapping("/inventory")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public List<ReportDto> getInventoryReports() {
        return reportService.getReportsByType("inventory");
    }

    // Obtener reportes de tipo "trends", solo rol ADMIN
    @GetMapping("/trends")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ReportDto> getTrendsReports() {
        return reportService.getReportsByType("trends");
    }

    // Crear un reporte nuevo
    @PostMapping("/create")
    public ReportDto createReport(@RequestBody ReportDto reportDto) {
        return reportService.createReport(reportDto);
    }

    // Eliminar un reporte por id
    @DeleteMapping("/delete/{id}")
    public String deleteReport(@PathVariable Long id) {
        return reportService.deleteReport(id);
    }
}
