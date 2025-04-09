package com.erp.ERP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.ERP.models.Client;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

  // 1. Buscar clientes por nombre exacto
  List<Client> findByFirstName(String firstName);

  // 2. Buscar clientes por apellido exacto
  List<Client> findByLastName(String lastName);

  // 3. Buscar clientes cuyo nombre contenga algo (sin importar
  // mayúsculas/minúsculas)
  List<Client> findByFirstNameContainingIgnoreCase(String keyword);

  // 4. Buscar clientes cuyo apellido contenga algo
  List<Client> findByLastNameContainingIgnoreCase(String keyword);

  // 5. Buscar cliente por email (email es único)
  Client findByEmail(String email);

  // 6. Verificar si existe un cliente por email (útil antes de registrar uno
  // nuevo)
  boolean existsByEmail(String email);
}
