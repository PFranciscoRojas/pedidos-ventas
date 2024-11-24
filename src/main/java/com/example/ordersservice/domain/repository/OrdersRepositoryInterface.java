package com.example.ordersservice.domain.repository;

import com.example.ordersservice.infraestructure.entity.Orders;

import java.util.List;
import java.util.Optional;

public interface OrdersRepositoryInterface {
    List<Orders> getAllOrders();
    Optional<Orders> getOrderById(Long id);
    Orders saveOrder(Orders order); // Acepta y devuelve Orders (no DTO)
    void deleteOrder(Long id);
}
