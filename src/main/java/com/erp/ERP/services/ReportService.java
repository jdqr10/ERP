package com.erp.ERP.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.ERP.dto.ReportDto;
import com.erp.ERP.models.Report;
import com.erp.ERP.models.User;
import com.erp.ERP.repository.ReportRepository;
import com.erp.ERP.repository.UserRepository;

@Service
public class ReportService {
    
    @Autowired
    private ReportRepository reportRepository;

    //Get method to retrieve all reports
    public List<ReportDto> findAll() {
        List<ReportDto> reportToReturn = new ArrayList<>();
        List<Report> reports = reportRepository.findAll();

        for (Report report : reports) {
            ReportDto reportDto = new ReportDto();
            reportToReturn.add(reportDto);
        }

        return reportToReturn;
    }


    //Get method to retrieve a report by user id
    @Autowired
    private UserRepository userRepository;

    public List<ReportDto> findByUserId(Long userId) {
    List<ReportDto> reportToReturn = new ArrayList<>();

    // lookup the user by id
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + userId));

    // search for reports by user
    List<Report> reports = reportRepository.findByUser(user);

    for (Report report : reports) {
        ReportDto reportDto = new ReportDto();
        // Set the properties of reportDto from report
        reportDto.setId(report.getId());
        reportDto.setGenerationDate(report.getGenerationDate());
        reportDto.setFilePath(report.getFilePath());
        reportDto.setType(report.getType());
        reportDto.setClientId(report.getClient().getId());
        reportDto.setUserId(report.getUser().getId());
        reportDto.setOrderId(report.getOrder().getId());

        reportToReturn.add(reportDto);
    }

    return reportToReturn;
}

  

}
