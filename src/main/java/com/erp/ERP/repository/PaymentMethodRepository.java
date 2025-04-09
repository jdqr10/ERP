package com.erp.ERP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.ERP.models.PaymentMethod;

import java.util.Optional;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

  // 1. Buscar método de pago por nombre
  Optional<PaymentMethod> findByName(String name);

  // 2. Verificar si existe un método de pago por nombre
  boolean existsByName(String name);

  // 3. Eliminar un método de pago por nombre
  void deleteByName(String name);
}
