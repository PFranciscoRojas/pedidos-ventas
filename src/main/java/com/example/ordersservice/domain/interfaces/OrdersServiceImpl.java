package com.example.ordersservice.domain.interfaces;

import com.example.ordersservice.domain.service.OrdersService;
import com.example.ordersservice.infraestructure.crud_interface.OrdersCrudRepository;
import com.example.ordersservice.infraestructure.entity.Orders;
import com.example.ordersservice.exception.OrdersException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersCrudRepository ordersCrudRepository;

    @Override
    public Orders createOrder(Orders order) {
        order.setStatus("PENDING");
        return ordersCrudRepository.save(order);
    }

    @Override
    public Optional<Orders> getOrderById(Long id) {
        return ordersCrudRepository.findById(id);
    }

    @Override
    public List<Orders> listOrders(Long customerId, String status) {
        if (customerId != null) {
            return ordersCrudRepository.findByCustomer_CustomerId(customerId);

        } else if (status != null) {
            return ordersCrudRepository.findByStatus(status);
        }
        return StreamSupport.stream(ordersCrudRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Orders updateOrderStatus(Long id, String status) {
        Orders order = ordersCrudRepository.findById(id)
                .orElseThrow(() -> new OrdersException("Order not found for ID: " + id));
        order.setStatus(status);
        return ordersCrudRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        if (!ordersCrudRepository.existsById(id)) {
            throw new OrdersException("Order not found for ID: " + id);
        }
        ordersCrudRepository.deleteById(id);
    }
}
