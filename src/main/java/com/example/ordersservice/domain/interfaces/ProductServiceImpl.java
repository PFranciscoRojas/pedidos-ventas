package com.example.ordersservice.domain.interfaces;

import com.example.ordersservice.domain.service.ProductService;
import com.example.ordersservice.infraestructure.crud_interface.ProductCrudRepository;
import com.example.ordersservice.infraestructure.entity.Product;
import com.example.ordersservice.exception.OrdersException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductCrudRepository productCrudRepository;

    @Override
    public Product addProduct(Product product) {
        return productCrudRepository.save(product);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productCrudRepository.findById(id);
    }

    @Override
    public List<Product> listProducts() {
        return StreamSupport.stream(productCrudRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Product updateProductStock(Long id, int stock) {
        Product product = productCrudRepository.findById(id)
                .orElseThrow(() -> new OrdersException("Product not found for ID: " + id));
        product.setStock(stock);
        return productCrudRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productCrudRepository.existsById(id)) {
            throw new OrdersException("Product not found for ID: " + id);
        }
        productCrudRepository.deleteById(id);
    }
}
