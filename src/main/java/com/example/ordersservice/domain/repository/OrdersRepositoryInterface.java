package com.example.ordersservice.domain.repository;

import com.example.ordersservice.domain.dto.OrdersDTO;

import java.util.List;
import java.util.Optional;

public interface OrdersRepositoryInterface {

    OrdersDTO saveOrder(OrdersDTO ordersDTO);

    Optional<OrdersDTO> getOrderById(Long id);

    List<OrdersDTO> getAllOrders();

    void deleteOrder(Long id);
}
