package com.erp.ERP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.ERP.models.User;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  // Buscar usuario por email
  Optional<User> findByEmail(String email);

  // Verificar si un usuario existe por email
  boolean existsByEmail(String email);

  // Buscar todos los usuarios que tienen un rol específico
  List<User> findByRoleId(Long roleId);

  // Buscar por nombre o apellido
  List<User> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
}
