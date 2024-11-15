package com.example.ordersservice.domain.repository;

import com.example.ordersservice.infraestructure.entity.OrdersDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrdersDetail, Long> {
    
}
