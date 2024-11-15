package com.example.ordersservice.domain.service;

import com.example.ordersservice.domain.repository.ProductRepository;
import com.example.ordersservice.infraestructure.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    public Product updateProductStock(Long productId, int newStock) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found for ID: " + productId));
        if (newStock < 0) {
            throw new RuntimeException("Stock cannot be negative.");
        }
        product.setStock(newStock);
        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
