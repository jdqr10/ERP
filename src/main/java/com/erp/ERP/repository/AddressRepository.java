package com.erp.ERP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.ERP.models.Address;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

  List<Address> findByAddress(String address);

  List<Address> findByAddressContainingIgnoreCase(String keyword);

  boolean existsByAddress(String address);
  
  void deleteByAddress(String address);
}
