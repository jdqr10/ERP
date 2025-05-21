package com.erp.ERP.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.ERP.dto.ReportDto;
import com.erp.ERP.services.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    
    @Autowired
    private ReportService reportService;    

    //get all reports
    @GetMapping("/getAllReports")
    public List<ReportDto> getAllReports() {
        return reportService.findAll();
    }

    //get report by user id
    @GetMapping("/getReportByUserId/{userId}")
    public List<ReportDto> getReportByUserId(@PathVariable Long userId) {
        return reportService.findByUserId(userId);
    }


}
