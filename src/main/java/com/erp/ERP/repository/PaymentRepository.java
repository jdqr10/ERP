package com.erp.ERP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.ERP.models.Order;
import com.erp.ERP.models.Payment;
import com.erp.ERP.models.PaymentMethod;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

  // 1. Buscar todos los pagos de una orden
  List<Payment> findByOrder(Order order);

  // 2. Buscar todos los pagos por método de pago (ej: tarjeta, efectivo, etc.)
  List<Payment> findByPaymentMethod(PaymentMethod paymentMethod);

  // 3. Buscar pagos por estado (ej: "PENDING", "PAID", "CANCELLED")
  List<Payment> findByStatus(String status);

  // 4. Buscar pagos de una orden con un estado específico
  List<Payment> findByOrderAndStatus(Order order, String status);

  // 5. Verificar si existe algún pago para una orden
  boolean existsByOrder(Order order);

  // 6. Eliminar todos los pagos asociados a una orden (ej: si se cancela la
  // orden)
  void deleteByOrder(Order order);
}
