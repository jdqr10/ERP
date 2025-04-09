package com.erp.ERP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.ERP.models.Client;
import com.erp.ERP.models.Order;
import com.erp.ERP.models.Report;
import com.erp.ERP.models.User;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

  List<Report> findByClient(Client client);

  List<Report> findByUser(User user);

  List<Report> findByOrder(Order order);

  List<Report> findByType(String type);

  List<Report> findByGenerationDate(LocalDate generationDate);
  
  List<Report> findByGenerationDateBetween(LocalDate startDate, LocalDate endDate);
}
