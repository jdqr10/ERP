package com.erp.ERP.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.ERP.dto.OrderDto;
import com.erp.ERP.models.Order;
import com.erp.ERP.repository.OrderRepository;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    //Get method to retieve all orders
    public List<OrderDto> findAll() {
        List<OrderDto> orderToReturn = new ArrayList<>();
        List<Order> orders = orderRepository.findAll();

        for (Order order : orders) {
            OrderDto orderDto = new OrderDto();
            orderToReturn.add(orderDto);
        }

        return orderToReturn;
    }

    //Post method to create a new order
    public OrderDto createOrder(OrderDto request) {
        Order order = new Order();
        order.setOrderDate(request.getOrderDate());
        order.setCustomerName(request.getCustomerName());
        order.setTotalAmount(request.getTotalAmount());

        // save the order to the database
        Order savedOrder = orderRepository.save(order);

        // Convert the saved order to OrderDto
        return new OrderDto();
    }

    //Delete method to delete an order
    public String deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            return "Order with id " + id + " does not exist";
        }else{
            orderRepository.deleteById(id);
            return "Order with id " + id + " deleted successfully";
        }
    }

    //Put method to update an order
    public OrderDto updateOrder(Long id, OrderDto request) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setOrderDate(request.getOrderDate());
                    order.setCustomerName(request.getCustomerName());
                    order.setTotalAmount(request.getTotalAmount());
                    Order updatedOrder = orderRepository.save(order);
                    return new OrderDto();
                })
                .orElse(null);
    }
    
}
