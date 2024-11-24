package com.example.ordersservice.infraestructure.crud_interface;

import com.example.ordersservice.infraestructure.entity.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrdersCrudRepository extends CrudRepository<Orders, Long> {
    
}