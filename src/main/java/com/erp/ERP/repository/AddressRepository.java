package com.erp.ERP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.ERP.models.Address;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

  // 1. Buscar una dirección exacta
  List<Address> findByAddress(String address);

  // 2. Buscar direcciones que contengan una palabra (sin importar
  // mayúsculas/minúsculas)
  List<Address> findByAddressContainingIgnoreCase(String keyword);

  // 3. Verificar si existe una dirección exacta (muy útil para validaciones)
  boolean existsByAddress(String address);

  // 4. Eliminar por dirección exacta
  void deleteByAddress(String address);
}
