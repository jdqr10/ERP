package com.erp.ERP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.ERP.models.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  // 1. Buscar producto por nombre
  Optional<Product> findByName(String name);

  // 2. Verificar si existe un producto por nombre
  boolean existsByName(String name);

  // 3. Buscar productos que tengan stock mayor a 0
  List<Product> findByStockGreaterThan(Integer stock);

  // 4. Buscar productos por rango de precio
  List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

  // 5. Buscar productos que contengan cierta palabra clave en la descripción
  List<Product> findByDescriptionContainingIgnoreCase(String keyword);
}
