package com.erp.ERP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.ERP.models.Order;
import com.erp.ERP.models.OrderProduct;
import com.erp.ERP.models.Product;

import java.util.List;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

  List<OrderProduct> findByOrder(Order order);

  List<OrderProduct> findByProduct(Product product);

  OrderProduct findByOrderAndProduct(Order order, Product product);

  boolean existsByOrderAndProduct(Order order, Product product);

  void deleteByOrder(Order order);
  
  void deleteByProduct(Product product);
}
