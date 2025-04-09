package com.erp.ERP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.ERP.models.Order;
import com.erp.ERP.models.Payment;
import com.erp.ERP.models.PaymentMethod;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

  List<Payment> findByOrder(Order order);

  List<Payment> findByPaymentMethod(PaymentMethod paymentMethod);

  List<Payment> findByStatus(String status);

  List<Payment> findByOrderAndStatus(Order order, String status);

  boolean existsByOrder(Order order);
  
  void deleteByOrder(Order order);
}
