package com.example.ordersservice.infraestructure.repository;

import com.example.ordersservice.domain.dto.ProductDTO;
import com.example.ordersservice.domain.repository.ProductRepositoryInterface;
import com.example.ordersservice.infraestructure.crud_interface.ProductCrudRepository;
import com.example.ordersservice.infraestructure.entity.Product;
import com.example.ordersservice.infraestructure.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductRepository implements ProductRepositoryInterface {

    @Autowired
    private ProductCrudRepository productCrudRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = (List<Product>) productCrudRepository.findAll();
        return products.stream().map(productMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDTO> getProductById(Long id) {
        return productCrudRepository.findById(id).map(productMapper::toDTO);
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        return productMapper.toDTO(productCrudRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) {
        productCrudRepository.deleteById(id);
    }
}
