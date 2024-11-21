package com.example.ordersservice.controller;

import com.example.ordersservice.domain.dto.ProductDTO;
import com.example.ordersservice.domain.service.ProductService;
import com.example.ordersservice.infraestructure.mappers.ProductMapper;
import jakarta.validation.Valid;
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

    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO) {
        var savedProduct = productService.addProduct(productMapper.toEntity(productDTO));
        return ResponseEntity.ok(productMapper.toDTO(savedProduct));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(product -> ResponseEntity.ok(productMapper.toDTO(product)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> listProducts() {
        var products = productService.listProducts();
        return ResponseEntity.ok(products.stream().map(productMapper::toDTO).toList());
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<ProductDTO> updateProductStock(@PathVariable Long id, @RequestBody int newStock) {
        var updatedProduct = productService.updateProductStock(id, newStock);
        return ResponseEntity.ok(productMapper.toDTO(updatedProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully.");
    }
}
