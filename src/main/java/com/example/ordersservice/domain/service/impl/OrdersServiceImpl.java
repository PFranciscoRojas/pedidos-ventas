package com.example.ordersservice.domain.service.impl;

import com.example.ordersservice.domain.dto.OrdersDTO;
import com.example.ordersservice.domain.service.OrdersService;
import com.example.ordersservice.infraestructure.entity.Customer;
import com.example.ordersservice.infraestructure.entity.Orders;
import com.example.ordersservice.infraestructure.mapper.OrdersMapper;
import com.example.ordersservice.infraestructure.repository.CustomerRepository;
import com.example.ordersservice.infraestructure.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrdersMapper ordersMapper; // Asegúrate de que OrdersMapper esté correctamente definido

    @Override
    public Orders createOrder(Orders order) {
        if (order == null || order.getCustomer() == null || order.getCustomer().getCustomerId() == null) {
            throw new IllegalArgumentException("Invalid order or customer details");
        }

        Customer customer = customerRepository.findById(order.getCustomer().getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        order.setCustomer(customer);
        return ordersRepository.saveOrder(order);
    }

    @Override
    public Optional<Orders> getOrderById(Long id) {
        return ordersRepository.getOrderById(id);
    }

    @Override
    public List<Orders> listOrders(Long customerId, String status) {
        return ordersRepository.getAllOrders().stream()
                .filter(order -> customerId == null || order.getCustomer().getCustomerId().equals(customerId))
                .filter(order -> status == null || order.getStatus().equalsIgnoreCase(status))
                .toList();
    }

    @Override
    public Orders updateOrderStatus(Long id, String status) {
        Orders order = ordersRepository.getOrderById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        order.setStatus(status);
        return ordersRepository.saveOrder(order);
    }

    @Override
    public void deleteOrder(Long id) {
        ordersRepository.deleteOrder(id);
    }

    @Override
    public List<OrdersDTO> getAllOrders() {
        return ordersRepository.getAllOrders().stream()
                .map(ordersMapper::toDTO)
                .toList();
    }
}
