package com.example.ordersservice.domain.service.impl;

import com.example.ordersservice.domain.dto.OrdersDetailDTO;
import com.example.ordersservice.domain.repository.OrdersDetailRepositoryInterface;
import com.example.ordersservice.domain.service.OrdersDetailService;
import com.example.ordersservice.infraestructure.entity.OrdersDetail;
import com.example.ordersservice.infraestructure.mapper.OrdersDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersDetailServiceImpl implements OrdersDetailService {

    @Autowired
    private OrdersDetailRepositoryInterface ordersDetailRepository;

    @Autowired
    private OrdersDetailMapper ordersDetailMapper;

    @Override
    public List<OrdersDetailDTO> getAllOrderDetails() {
        // Convierte entidades a DTOs correctamente
        return ordersDetailRepository.getAllOrderDetails().stream()
                .map(ordersDetail -> ordersDetailMapper.toDTO((OrdersDetail) ordersDetail)) // Casting explícito
                .toList();
    }

    @Override
    public Optional<OrdersDetailDTO> getOrderDetailById(Long id) {
        // Convierte entidad a DTO correctamente
        return ordersDetailRepository.getOrderDetailById(id)
                .map(ordersDetail -> ordersDetailMapper.toDTO((OrdersDetail) ordersDetail)); // Casting explícito
    }

    @Override
    public OrdersDetailDTO createOrderDetail(OrdersDetailDTO ordersDetailDTO) {
        // Convierte DTO a entidad, guarda y convierte nuevamente a DTO
        OrdersDetail ordersDetailEntity = ordersDetailMapper.toEntity(ordersDetailDTO);
        OrdersDetail savedDetail = (OrdersDetail) ordersDetailRepository.saveOrderDetail(ordersDetailEntity);
        return ordersDetailMapper.toDTO(savedDetail);
    }

    @Override
    public void deleteOrderDetail(Long id) {
        // Elimina el detalle
        ordersDetailRepository.deleteOrderDetail(id);
    }
}
