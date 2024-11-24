package com.example.ordersservice.domain.service.impl;

import com.example.ordersservice.domain.service.ProductService;
import com.example.ordersservice.infraestructure.entity.Product;
import com.example.ordersservice.infraestructure.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return productRepository.saveProduct(product);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.getProductById(id);
    }

    @Override
    public List<Product> listProducts() {
        return productRepository.getAllProducts();
    }

    @Override
    public Product updateProductStock(Long id, int stock) {
        Product product = productRepository.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        product.setStock(stock);
        return productRepository.saveProduct(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteProduct(id);
    }
}
