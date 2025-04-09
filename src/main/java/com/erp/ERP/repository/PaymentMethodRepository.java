package com.erp.ERP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.ERP.models.PaymentMethod;

import java.util.Optional;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

  Optional<PaymentMethod> findByName(String name);

  boolean existsByName(String name);
  
  void deleteByName(String name);
}
