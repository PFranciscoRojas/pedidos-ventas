package com.example.ordersservice.domain.repository;

import com.example.ordersservice.infraestructure.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryInterface {
    List<Product> getAllProducts();
    Optional<Product> getProductById(Long id);
    Product saveProduct(Product product); // Acepta y devuelve Product
    void deleteProduct(Long id);
}
