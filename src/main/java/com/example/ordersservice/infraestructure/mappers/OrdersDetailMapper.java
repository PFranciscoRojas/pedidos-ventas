package com.example.ordersservice.infraestructure.mappers;

import com.example.ordersservice.domain.dto.OrdersDetailDTO;
import com.example.ordersservice.infraestructure.entity.Orders;
import com.example.ordersservice.infraestructure.entity.OrdersDetail;
import com.example.ordersservice.infraestructure.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class OrdersDetailMapper {

    public OrdersDetailDTO toDTO(OrdersDetail detail) {
        if (detail == null) {
            return null;
        }
        OrdersDetailDTO dto = new OrdersDetailDTO();
        dto.setDetailId(detail.getDetailId());
        dto.setOrderId(detail.getOrder().getOrderId());
        dto.setProductId(detail.getProduct().getProductId());
        dto.setProductName(detail.getProduct().getName()); // Asignar el nombre del producto
        dto.setProductDescription(detail.getProduct().getDescription()); // Asignar la descripción del producto
        dto.setQuantity(detail.getQuantity());
        dto.setPrice(detail.getPrice());
        return dto;
    }

    public OrdersDetail toEntity(OrdersDetailDTO dto, Orders order) {
        if (dto == null || order == null) {
            return null;
        }

        OrdersDetail detail = new OrdersDetail();
        detail.setDetailId(dto.getDetailId());
        detail.setQuantity(dto.getQuantity());
        detail.setPrice(dto.getPrice());
        detail.setOrder(order);

        if (dto.getProductId() != null) {
            Product product = new Product();
            product.setProductId(dto.getProductId());
            detail.setProduct(product);
        }

        return detail;
    }

    public OrdersDetail toEntity(OrdersDetailDTO dto, Orders order, Product product) {
        if (dto == null || order == null || product == null) {
            return null;
        }

        OrdersDetail detail = toEntity(dto, order);
        detail.setProduct(product);
        return detail;
    }
}
