package com.erp.ERP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.ERP.models.Address;
import com.erp.ERP.models.Client;
import com.erp.ERP.models.ClientAddress;

import java.util.List;

@Repository
public interface ClientAddressRepository extends JpaRepository<ClientAddress, Long> {

  List<ClientAddress> findByClient(Client client);

  List<ClientAddress> findByAddress(Address address);

  ClientAddress findByClientAndAddress(Client client, Address address);

  boolean existsByClientAndAddress(Client client, Address address);

  void deleteByClient(Client client);
  
  void deleteByAddress(Address address);
}
