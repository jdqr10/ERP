package com.erp.ERP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.ERP.models.Client;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

  List<Client> findByFirstName(String firstName);

  List<Client> findByLastName(String lastName);

  List<Client> findByFirstNameContainingIgnoreCase(String keyword);

  List<Client> findByLastNameContainingIgnoreCase(String keyword);

  Client findByEmail(String email);
  
  boolean existsByEmail(String email);
}