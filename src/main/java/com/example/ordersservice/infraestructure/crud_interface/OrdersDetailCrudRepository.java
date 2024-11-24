package com.example.ordersservice.infraestructure.crud_interface;

import com.example.ordersservice.infraestructure.entity.OrdersDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersDetailCrudRepository extends CrudRepository<OrdersDetail, Long> {
}
