package com.example.ordersservice.domain.repository;

import com.example.ordersservice.infraestructure.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Additional custom queries can go here if needed
}
