package com.erp.ERP.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.erp.ERP.services.OrderService;
import com.erp.ERP.dto.OrderDto;

import org.springframework.web.bind.annotation.RestController;

import com.erp.ERP.dto.OrderDto;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    //get all orders
    @GetMapping("/getAllOrders")
    public List<OrderDto> getAllOrders() {
        return orderService.findAll();
    }

    //save a new order
    @PostMapping("/createOrder")
    public OrderDto createOrder(@RequestBody OrderDto request) {
        return orderService.createOrder(request);
    }

    //update an existing order
    @PutMapping("updateOrder/{id}")
    public OrderDto updateOrder(@PathVariable Long id, @RequestBody OrderDto request) {
        return orderService.updateOrder(id, request);
    }

    //Delete an order by its id
    @DeleteMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable Long id) {
        return orderService.deleteOrder(id);
    }

}
