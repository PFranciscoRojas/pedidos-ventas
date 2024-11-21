package com.example.ordersservice.domain.repository;

import com.example.ordersservice.domain.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryInterface {
    List<ProductDTO> getAllProducts();
    Optional<ProductDTO> getProductById(Long id);
    ProductDTO saveProduct(ProductDTO productDTO);
    void deleteProduct(Long id);
}
