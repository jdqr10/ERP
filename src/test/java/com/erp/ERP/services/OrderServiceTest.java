package com.erp.ERP.services;

import com.erp.ERP.dto.OrderDto;
import com.erp.ERP.models.Order;
import com.erp.ERP.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private Order order1;
    private Order order2;
    private OrderDto orderDto1;

    @BeforeEach
    void setUp() {
        order1 = new Order();
        order1.setId(1L);
        order1.setOrderDate(LocalDate.of(2023, 1, 15));
        order1.setCustomerName("John Doe");
        order1.setTotalAmount(100.0);

        order2 = new Order();
        order2.setId(2L);
        order2.setOrderDate(LocalDate.of(2023, 1, 16));
        order2.setCustomerName("Jane Smith");
        order2.setTotalAmount(200.0);

        orderDto1 = new OrderDto();
        orderDto1.setOrderDate(LocalDate.of(2023, 1, 15));
        orderDto1.setCustomerName("John Doe");
        orderDto1.setTotalAmount(100.0);
    }

    @Test
    void findAll_ShouldReturnAllOrders() {
        // Arrange
        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));

        // Act
        List<OrderDto> result = orderService.findAll();

        // Assert
        assertEquals(2, result.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void createOrder_ShouldSaveAndReturnOrderDto() {
        // Arrange
        when(orderRepository.save(any(Order.class))).thenReturn(order1);

        // Act
        OrderDto result = orderService.createOrder(orderDto1);

        // Assert
        assertNotNull(result);
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void deleteOrder_WhenOrderExists_ShouldDeleteAndReturnSuccessMessage() {
        // Arrange
        Long orderId = 1L;
        when(orderRepository.existsById(orderId)).thenReturn(true);

        // Act
        String result = orderService.deleteOrder(orderId);

        // Assert
        assertEquals("Order with id " + orderId + " deleted successfully", result);
        verify(orderRepository, times(1)).deleteById(orderId);
    }

    @Test
    void deleteOrder_WhenOrderNotExists_ShouldReturnNotFoundMessage() {
        // Arrange
        Long orderId = 99L;
        when(orderRepository.existsById(orderId)).thenReturn(false);

        // Act
        String result = orderService.deleteOrder(orderId);

        // Assert
        assertEquals("Order with id " + orderId + " does not exist", result);
        verify(orderRepository, never()).deleteById(orderId);
    }

    @Test
    void updateOrder_WhenOrderExists_ShouldUpdateAndReturnOrderDto() {
        // Arrange
        Long orderId = 1L;
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order1));
        when(orderRepository.save(any(Order.class))).thenReturn(order1);

        // Act
        OrderDto result = orderService.updateOrder(orderId, orderDto1);

        // Assert
        assertNotNull(result);
        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, times(1)).save(order1);
    }

    @Test
    void updateOrder_WhenOrderNotExists_ShouldReturnNull() {
        // Arrange
        Long orderId = 99L;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // Act
        OrderDto result = orderService.updateOrder(orderId, orderDto1);

        // Assert
        assertNull(result);
        verify(orderRepository, never()).save(any(Order.class));
    }
}