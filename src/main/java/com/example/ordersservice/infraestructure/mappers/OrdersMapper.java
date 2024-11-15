package com.example.ordersservice.infraestructure.mappers;

import com.example.ordersservice.domain.dto.OrdersDTO;
import com.example.ordersservice.infraestructure.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrdersMapper {

    @Autowired
    private OrdersDetailMapper ordersDetailMapper;

    public OrdersDTO toDTO(Orders order) {
        if (order == null) {
            return null;
        }
        OrdersDTO dto = new OrdersDTO();
        dto.setOrderId(order.getOrderId());
        dto.setCustomerId(order.getCustomerId());
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getStatus());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setOrderDetails(order.getOrderDetails()
                                 .stream()
                                 .map(ordersDetailMapper::toDTO)
                                 .collect(Collectors.toList()));
        return dto;
    }

    public Orders toEntity(OrdersDTO dto) {
        if (dto == null) {
            return null;
        }
        Orders order = new Orders();
        order.setOrderId(dto.getOrderId());
        order.setCustomerId(dto.getCustomerId());
        order.setOrderDate(dto.getOrderDate());
        order.setStatus(dto.getStatus());
        order.setTotalAmount(dto.getTotalAmount());
        order.setOrderDetails(dto.getOrderDetails()
                                 .stream()
                                 .map(detailDto -> ordersDetailMapper.toEntity(detailDto, order))
                                 .collect(Collectors.toList()));
        return order;
    }
}
