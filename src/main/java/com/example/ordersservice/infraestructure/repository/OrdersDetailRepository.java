package com.example.ordersservice.infraestructure.repository;

import com.example.ordersservice.domain.repository.OrdersDetailRepositoryInterface;
import com.example.ordersservice.infraestructure.crud_interface.OrdersDetailCrudRepository;
import com.example.ordersservice.infraestructure.entity.OrdersDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrdersDetailRepository implements OrdersDetailRepositoryInterface {

    @Autowired
    private OrdersDetailCrudRepository ordersDetailCrudRepository;

    @Override
    public List<OrdersDetail> getAllOrderDetails() {
        // Retorna directamente las entidades desde el repositorio CRUD
        return (List<OrdersDetail>) ordersDetailCrudRepository.findAll();
    }

    @Override
    public Optional<OrdersDetail> getOrderDetailById(Long id) {
        // Retorna un Optional de la entidad
        return ordersDetailCrudRepository.findById(id);
    }

    @Override
    public OrdersDetail saveOrderDetail(OrdersDetail ordersDetail) {
        // Guarda la entidad y retorna la entidad guardada
        return ordersDetailCrudRepository.save(ordersDetail);
    }

    @Override
    public void deleteOrderDetail(Long id) {
        // Elimina el detalle por ID
        ordersDetailCrudRepository.deleteById(id);
    }
}
