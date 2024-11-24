package com.example.ordersservice.infraestructure.repository;

import com.example.ordersservice.domain.repository.ProductRepositoryInterface;
import com.example.ordersservice.infraestructure.crud_interface.ProductCrudRepository;
import com.example.ordersservice.infraestructure.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository implements ProductRepositoryInterface {

    @Autowired
    private ProductCrudRepository productCrudRepository;

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) productCrudRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productCrudRepository.findById(id);
    }

    @Override
    public Product saveProduct(Product product) {
        return productCrudRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productCrudRepository.deleteById(id);
    }
}
