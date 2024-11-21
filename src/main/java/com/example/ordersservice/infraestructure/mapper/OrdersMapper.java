package com.example.ordersservice.infraestructure.mapper;

import com.example.ordersservice.domain.dto.OrdersDTO;
import com.example.ordersservice.domain.dto.ProductDTO;
import com.example.ordersservice.infraestructure.entity.Customer;
import com.example.ordersservice.infraestructure.entity.Orders;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrdersMapper {

    public OrdersDTO toDTO(Orders order) {
        if (order == null) return null;
        return new OrdersDTO(
                order.getOrderId(),
                order.getCustomer().getCustomerId(),
                order.getOrderDate(),
                order.getStatus(),
                order.getTotalAmount(),
                order.getOrderDetails().stream()
                        .map(detail -> new ProductDTO(
                                detail.getProduct().getName(),
                                detail.getProduct().getDescription()
                        ))
                        .collect(Collectors.toList())
        );
    }

    public Orders toEntity(OrdersDTO ordersDTO, Customer customer) {
        if (ordersDTO == null) return null;
        Orders order = new Orders();
        order.setOrderId(ordersDTO.getOrderId());
        order.setCustomer(customer);
        order.setOrderDate(ordersDTO.getOrderDate());
        order.setStatus(ordersDTO.getStatus());
        order.setTotalAmount(ordersDTO.getTotalAmount());
        return order;
    }
}