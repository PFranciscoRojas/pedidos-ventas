package com.example.ordersservice.controller;

import com.example.ordersservice.domain.dto.ProductDTO;
import com.example.ordersservice.domain.service.ProductService;
import com.example.ordersservice.infraestructure.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(
                productService.listProducts().stream()
                        .map(productMapper::toDTO)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(productMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
        var savedProduct = productService.addProduct(productMapper.toEntity(productDTO));
        return ResponseEntity.ok(productMapper.toDTO(savedProduct));
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<ProductDTO> updateProductStock(@PathVariable Long id, @RequestBody int stock) {
        var updatedProduct = productService.updateProductStock(id, stock);
        return ResponseEntity.ok(productMapper.toDTO(updatedProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
