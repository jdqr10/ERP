package com.erp.ERP.controller;

import com.erp.ERP.dto.OrderDto;
import com.erp.ERP.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class OrderControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    private OrderDto orderDto1;
    private OrderDto orderDto2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();

        orderDto1 = new OrderDto();
        orderDto1.setId(1L);
        orderDto1.setOrderDate(LocalDate.of(2023, 1, 15));
        orderDto1.setCustomerName("John Doe");
        orderDto1.setTotalAmount(100.0);

        orderDto2 = new OrderDto();
        orderDto2.setId(2L);
        orderDto2.setOrderDate(LocalDate.of(2023, 1, 16));
        orderDto2.setCustomerName("Jane Smith");
        orderDto2.setTotalAmount(200.0);
    }

    @Test
    void getAllOrders_ShouldReturnAllOrders() throws Exception {
        // Arrange
        List<OrderDto> orders = Arrays.asList(orderDto1, orderDto2);
        when(orderService.findAll()).thenReturn(orders);

        // Act & Assert
        mockMvc.perform(get("/getAllOrders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].customerName").value("John Doe"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].customerName").value("Jane Smith"));
    }

    @Test
    void createOrder_ShouldSaveAndReturnOrder() throws Exception {
        // Arrange
        when(orderService.createOrder(any(OrderDto.class))).thenReturn(orderDto1);

        // Act & Assert
        mockMvc.perform(post("/createOrder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"orderDate\":\"2023-01-15\",\"customerName\":\"John Doe\",\"totalAmount\":100.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.customerName").value("John Doe"));
    }

    @Test
    void updateOrder_ShouldUpdateAndReturnOrder() throws Exception {
        // Arrange
        when(orderService.updateOrder(eq(1L), any(OrderDto.class))).thenReturn(orderDto1);

        // Act & Assert
        mockMvc.perform(put("/updateOrder/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"orderDate\":\"2023-01-15\",\"customerName\":\"John Doe\",\"totalAmount\":100.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.customerName").value("John Doe"));
    }

    @Test
    void deleteOrder_WhenOrderExists_ShouldReturnSuccessMessage() throws Exception {
        // Arrange
        when(orderService.deleteOrder(1L)).thenReturn("Order with id 1 deleted successfully");

        // Act & Assert
        mockMvc.perform(delete("/deleteOrder/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Order with id 1 deleted successfully"));
    }

    @Test
    void deleteOrder_WhenOrderNotExists_ShouldReturnNotFoundMessage() throws Exception {
        // Arrange
        when(orderService.deleteOrder(99L)).thenReturn("Order with id 99 does not exist");

        // Act & Assert
        mockMvc.perform(delete("/deleteOrder/99"))
                .andExpect(status().isOk())
                .andExpect(content().string("Order with id 99 does not exist"));
    }
}