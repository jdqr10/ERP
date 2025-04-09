package com.erp.ERP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.ERP.models.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  Optional<Product> findByName(String name);

  boolean existsByName(String name);

  List<Product> findByStockGreaterThan(Integer stock);

  List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
  
  List<Product> findByDescriptionContainingIgnoreCase(String keyword);
}
