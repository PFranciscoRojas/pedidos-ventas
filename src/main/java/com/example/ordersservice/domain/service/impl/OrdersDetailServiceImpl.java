package com.example.ordersservice.domain.service.impl;

import com.example.ordersservice.domain.dto.OrdersDetailDTO;
import com.example.ordersservice.domain.service.OrdersDetailService;
import com.example.ordersservice.infraestructure.mapper.OrdersDetailMapper;
import com.example.ordersservice.infraestructure.repository.OrdersDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersDetailServiceImpl implements OrdersDetailService {

    @Autowired
    private OrdersDetailRepository ordersDetailRepository;

    @Autowired
    private OrdersDetailMapper ordersDetailMapper;

    @Override
    public List<OrdersDetailDTO> getAllOrderDetails() {
        return ordersDetailRepository.getAllOrderDetails().stream()
                .map(ordersDetailMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<OrdersDetailDTO> getOrderDetailById(Long id) {
        return ordersDetailRepository.getOrderDetailById(id)
                .map(ordersDetailMapper::toDTO);
    }

    @Override
    public OrdersDetailDTO createOrderDetail(OrdersDetailDTO ordersDetailDTO) {
        var ordersDetailEntity = ordersDetailMapper.toEntity(ordersDetailDTO);
        var savedDetail = ordersDetailRepository.saveOrderDetail(ordersDetailEntity);
        return ordersDetailMapper.toDTO(savedDetail);
    }

    @Override
    public void deleteOrderDetail(Long id) {
        ordersDetailRepository.deleteOrderDetail(id);
    }
}
