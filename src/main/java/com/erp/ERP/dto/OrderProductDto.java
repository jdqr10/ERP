package com.erp.ERP.dto;

import com.erp.ERP.models.Order;
import com.erp.ERP.models.Product;

public class OrderProductDto {

    private Long id;
    private Order order;
    private Product product;
    private Integer quantity;
    private Double price;

    // Default constructor
    public OrderProductDto() {
    }

    // Constructor with parameters
    public OrderProductDto(Long id, Order order, Product product, Integer quantity, Double price) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}