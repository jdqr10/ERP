package com.erp.ERP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.ERP.models.Order;
import com.erp.ERP.models.OrderProduct;
import com.erp.ERP.models.Product;

import java.util.List;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

  // 1. Buscar todos los productos de una orden
  List<OrderProduct> findByOrder(Order order);

  // 2. Buscar todos los registros de un producto específico (en distintas
  // órdenes)
  List<OrderProduct> findByProduct(Product product);

  // 3. Buscar un producto específico dentro de una orden específica
  OrderProduct findByOrderAndProduct(Order order, Product product);

  // 4. Verificar si un producto ya está en una orden
  boolean existsByOrderAndProduct(Order order, Product product);

  // 5. Eliminar todos los productos de una orden
  void deleteByOrder(Order order);

  // 6. Eliminar todos los registros relacionados a un producto
  void deleteByProduct(Product product);
}
