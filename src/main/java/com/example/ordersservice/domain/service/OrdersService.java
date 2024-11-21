package com.example.ordersservice.domain.service;

import com.example.ordersservice.domain.dto.OrdersDTO;
import com.example.ordersservice.infraestructure.entity.Orders;

import java.util.List;
import java.util.Optional;

public interface OrdersService {
    Orders createOrder(Orders order);

    Optional<Orders> getOrderById(Long id);

    List<Orders> listOrders(Long customerId, String status);

    Orders updateOrderStatus(Long id, String status);

    void deleteOrder(Long id);

    List<OrdersDTO> getAllOrders();
}
