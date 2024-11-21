package com.example.ordersservice.domain.service.impl;

import com.example.ordersservice.domain.dto.OrdersDTO;
import com.example.ordersservice.domain.repository.OrdersRepositoryInterface;
import com.example.ordersservice.domain.service.OrdersService;
import com.example.ordersservice.infraestructure.entity.Customer;
import com.example.ordersservice.infraestructure.entity.Orders;
import com.example.ordersservice.infraestructure.mapper.OrdersMapper;
import com.example.ordersservice.infraestructure.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersRepositoryInterface ordersRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public Orders createOrder(Orders order) {
        Customer customer = customerRepository.findById(order.getCustomer().getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        OrdersDTO orderDTO = ordersMapper.toDTO(order);
        OrdersDTO savedOrderDTO = ordersRepository.saveOrder(orderDTO);
        return ordersMapper.toEntity(savedOrderDTO, customer);
    }

    @Override
    public Optional<Orders> getOrderById(Long id) {
        return ordersRepository.getOrderById(id)
                .map(orderDTO -> {
                    Customer customer = customerRepository.findById(orderDTO.getCustomerId())
                            .orElse(null);
                    return ordersMapper.toEntity(orderDTO, customer);
                });
    }

    @Override
    public List<Orders> listOrders(Long customerId, String status) {
        return ordersRepository.getAllOrders().stream()
                .map(orderDTO -> {
                    Customer customer = customerRepository.findById(orderDTO.getCustomerId())
                            .orElse(null);
                    return ordersMapper.toEntity(orderDTO, customer);
                })
                .toList();
    }

    @Override
    public Orders updateOrderStatus(Long id, String status) {
        Orders order = getOrderById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        order.setStatus(status);

        OrdersDTO orderDTO = ordersMapper.toDTO(order);
        OrdersDTO updatedOrderDTO = ordersRepository.saveOrder(orderDTO);

        return ordersMapper.toEntity(updatedOrderDTO, order.getCustomer());
    }

    @Override
    public void deleteOrder(Long id) {
        ordersRepository.deleteOrder(id);
    }

    @Override
    public List<OrdersDTO> getAllOrders() {
        return ordersRepository.getAllOrders();
    }
}
