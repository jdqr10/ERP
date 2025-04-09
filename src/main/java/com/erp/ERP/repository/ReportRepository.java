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

  // 1. Buscar todos los reportes de un cliente
  List<Report> findByClient(Client client);

  // 2. Buscar todos los reportes de un usuario
  List<Report> findByUser(User user);

  // 3. Buscar todos los reportes de una orden
  List<Report> findByOrder(Order order);

  // 4. Buscar reportes por tipo
  List<Report> findByType(String type);

  // 5. Buscar reportes generados en una fecha específica
  List<Report> findByGenerationDate(LocalDate generationDate);

  // 6. Buscar reportes generados entre dos fechas
  List<Report> findByGenerationDateBetween(LocalDate startDate, LocalDate endDate);
}
