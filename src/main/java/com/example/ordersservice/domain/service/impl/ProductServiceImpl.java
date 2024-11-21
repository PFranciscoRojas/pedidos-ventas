package com.example.ordersservice.domain.service.impl;

import com.example.ordersservice.domain.repository.ProductRepositoryInterface;
import com.example.ordersservice.domain.service.ProductService;
import com.example.ordersservice.infraestructure.entity.Product;
import com.example.ordersservice.infraestructure.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepositoryInterface productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product addProduct(Product product) {
        return productMapper.toEntity(productRepository.saveProduct(productMapper.toDTO(product)));
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.getProductById(id).map(productMapper::toEntity);
    }

    @Override
    public List<Product> listProducts() {
        return productRepository.getAllProducts().stream()
                .map(productMapper::toEntity)
                .toList();
    }

    @Override
    public Product updateProductStock(Long id, int stock) {
        Product product = getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        product.setStock(stock);
        return productMapper.toEntity(productRepository.saveProduct(productMapper.toDTO(product)));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteProduct(id);
    }
}
