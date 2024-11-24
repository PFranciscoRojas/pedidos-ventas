package com.example.ordersservice.infraestructure.mapper;

import com.example.ordersservice.domain.dto.OrdersDetailDTO;
import com.example.ordersservice.infraestructure.entity.OrdersDetail;
import org.springframework.stereotype.Component;

@Component
public class OrdersDetailMapper {

    public OrdersDetailDTO toDTO(OrdersDetail detail) {
        if (detail == null) return null;
        return new OrdersDetailDTO(
                detail.getDetailId(),
                detail.getOrder().getOrderId(),
                detail.getProduct().getProductId(),
                detail.getProduct().getName(),
                detail.getProduct().getDescription(),
                detail.getQuantity(),
                detail.getPrice()
        );
    }

    public OrdersDetail toEntity(OrdersDetailDTO dto) {
        if (dto == null) return null;
        OrdersDetail detail = new OrdersDetail();
        detail.setDetailId(dto.getDetailId());
        detail.setQuantity(dto.getQuantity());
        detail.setPrice(dto.getPrice());
        return detail;
    }
}
