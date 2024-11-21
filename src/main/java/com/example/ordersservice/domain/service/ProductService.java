package com.example.ordersservice.domain.service;

import com.example.ordersservice.infraestructure.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product addProduct(Product product);

    Optional<Product> getProductById(Long id);

    List<Product> listProducts();

    Product updateProductStock(Long id, int stock);

    void deleteProduct(Long id);
}
