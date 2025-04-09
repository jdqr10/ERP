package com.erp.ERP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.ERP.models.Client;
import com.erp.ERP.models.Order;
import com.erp.ERP.models.User;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

  // 1. Buscar órdenes por cliente
  List<Order> findByClient(Client client);

  // 2. Buscar órdenes por usuario que las creó
  List<Order> findByUser(User user);

  // 3. Buscar órdenes por fecha específica
  List<Order> findByOrderDate(LocalDate orderDate);

  // 4. Buscar órdenes por estado
  List<Order> findByStatus(String status);

  // 5. Buscar órdenes por estado (ignorando mayúsculas/minúsculas)
  List<Order> findByStatusIgnoreCase(String status);

  // 6. Buscar órdenes entre dos fechas
  List<Order> findByOrderDateBetween(LocalDate startDate, LocalDate endDate);

  // 7. Contar órdenes de un cliente
  long countByClient(Client client);
}
