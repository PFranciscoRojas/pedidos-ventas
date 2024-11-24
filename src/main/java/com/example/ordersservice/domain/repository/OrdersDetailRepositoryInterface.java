package com.example.ordersservice.domain.repository;

import com.example.ordersservice.infraestructure.entity.OrdersDetail;

import java.util.List;
import java.util.Optional;

public interface OrdersDetailRepositoryInterface {

    List<OrdersDetail> getAllOrderDetails();

    Optional<OrdersDetail> getOrderDetailById(Long id);

    OrdersDetail saveOrderDetail(OrdersDetail ordersDetail);

    void deleteOrderDetail(Long id);
}
