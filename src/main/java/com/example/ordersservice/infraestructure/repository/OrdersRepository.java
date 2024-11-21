package com.example.ordersservice.infraestructure.repository;

import com.example.ordersservice.domain.dto.OrdersDTO;
import com.example.ordersservice.domain.repository.OrdersRepositoryInterface;
import com.example.ordersservice.infraestructure.crud_interface.OrdersCrudRepository;
import com.example.ordersservice.infraestructure.entity.Customer;
import com.example.ordersservice.infraestructure.entity.Orders;
import com.example.ordersservice.infraestructure.mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class OrdersRepository implements OrdersRepositoryInterface {

    @Autowired
    private OrdersCrudRepository ordersCrudRepository;

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public List<OrdersDTO> getAllOrders() {
        List<Orders> orders = (List<Orders>) ordersCrudRepository.findAll();
        return orders.stream().map(ordersMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<OrdersDTO> getOrderById(Long id) {
        return ordersCrudRepository.findById(id).map(ordersMapper::toDTO);
    }

    @Override
    public OrdersDTO saveOrder(OrdersDTO ordersDTO) {
        Customer customer = new Customer(); // Si tienes más lógica para el cliente, añádela aquí
        customer.setCustomerId(ordersDTO.getCustomerId());
        Orders order = ordersMapper.toEntity(ordersDTO, customer);
        return ordersMapper.toDTO(ordersCrudRepository.save(order));
    }

    @Override
    public void deleteOrder(Long id) {
        ordersCrudRepository.deleteById(id);
    }
}
