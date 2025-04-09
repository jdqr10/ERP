package com.erp.ERP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.ERP.models.Address;
import com.erp.ERP.models.Client;
import com.erp.ERP.models.ClientAddress;

import java.util.List;

@Repository
public interface ClientAddressRepository extends JpaRepository<ClientAddress, Long> {

  // 1. Buscar todas las relaciones de un cliente
  List<ClientAddress> findByClient(Client client);

  // 2. Buscar todas las relaciones de una dirección
  List<ClientAddress> findByAddress(Address address);

  // 3. Buscar una relación específica (cliente + dirección)
  ClientAddress findByClientAndAddress(Client client, Address address);

  // 4. Verificar si existe una relación cliente-dirección
  boolean existsByClientAndAddress(Client client, Address address);

  // 5. Eliminar todas las relaciones de un cliente
  void deleteByClient(Client client);

  // 6. Eliminar todas las relaciones de una dirección
  void deleteByAddress(Address address);
}
