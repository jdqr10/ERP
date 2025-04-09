package com.erp.ERP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.ERP.models.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  // Buscar un rol por su tipo (por ejemplo: "ADMIN", "USER", etc.)
  Optional<Role> findByType(String type);

  // Verificar si un rol existe por su tipo
  boolean existsByType(String type);

}
