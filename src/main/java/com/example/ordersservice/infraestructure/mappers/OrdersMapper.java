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
        return new OrdersDTO(
                order.getOrderId(),
                order.getCustomer().getCustomerId(),
                order.getOrderDate(),
                order.getStatus(),
                order.getTotalAmount(),
                order.getOrderDetails()
                     .stream()
                     .map(ordersDetailMapper::toDTO)
                     .collect(Collectors.toList())
        );
    }

    public Orders toEntity(OrdersDTO dto) {
        if (dto == null) {
            return null;
        }
        Orders order = new Orders();
        order.setOrderId(dto.getOrderId());
        order.setOrderDate(dto.getOrderDate());
        order.setStatus(dto.getStatus());
        order.setTotalAmount(dto.getTotalAmount());
        // Nota: Se necesita el Customer como entidad completa antes de mapear.
        order.setOrderDetails(dto.getOrderDetails()
                                 .stream()
                                 .map(detailDto -> ordersDetailMapper.toEntity(detailDto, order))
                                 .collect(Collectors.toList()));
        return order;
    }
}
