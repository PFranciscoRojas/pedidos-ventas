package com.example.ordersservice.infraestructure.crud_interface;

import com.example.ordersservice.infraestructure.entity.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersCrudRepository extends CrudRepository<Orders, Long> {
    List<Orders> findByCustomer_CustomerId(Long customerId);

    List<Orders> findByStatus(String status);
}
