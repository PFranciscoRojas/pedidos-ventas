package com.example.ordersservice.infraestructure.repository;

import com.example.ordersservice.domain.repository.OrdersRepositoryInterface;
import com.example.ordersservice.infraestructure.crud_interface.OrdersCrudRepository;
import com.example.ordersservice.infraestructure.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrdersRepository implements OrdersRepositoryInterface {

    @Autowired
    private OrdersCrudRepository ordersCrudRepository;

    @Override
    public List<Orders> getAllOrders() {
        return (List<Orders>) ordersCrudRepository.findAll();
    }

    @Override
    public Optional<Orders> getOrderById(Long id) {
        return ordersCrudRepository.findById(id);
    }

    @Override
    public Orders saveOrder(Orders order) {
        return ordersCrudRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        ordersCrudRepository.deleteById(id);
    }
}
