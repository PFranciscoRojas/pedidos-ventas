package com.example.ordersservice.domain.repository;

import com.example.ordersservice.domain.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryInterface {

    /**
     * Obtiene todos los productos como una lista de objetos ProductDTO.
     *
     * @return Lista de ProductDTO.
     */
    List<ProductDTO> getAllProducts();

    /**
     * Busca un producto por su ID.
     *
     * @param id ID del producto.
     * @return Optional que contiene el ProductDTO si existe, o vacío si no.
     */
    Optional<ProductDTO> getProductById(Long id);

    /**
     * Guarda un producto nuevo o actualiza uno existente.
     *
     * @param productDTO DTO del producto a guardar.
     * @return ProductDTO guardado o actualizado.
     */
    ProductDTO saveProduct(ProductDTO productDTO);

    /**
     * Elimina un producto específico por su ID.
     *
     * @param id ID del producto a eliminar.
     */
    void deleteProduct(Long id);
}
