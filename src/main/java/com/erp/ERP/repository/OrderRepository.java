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

  List<Order> findByClient(Client client);

  List<Order> findByUser(User user);

  List<Order> findByOrderDate(LocalDate orderDate);

  List<Order> findByStatus(String status);

  List<Order> findByStatusIgnoreCase(String status);

  List<Order> findByOrderDateBetween(LocalDate startDate, LocalDate endDate);
  
  long countByClient(Client client);
}
