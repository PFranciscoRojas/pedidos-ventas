package com.example.ordersservice.infraestructure.crud_interface;

import com.example.ordersservice.infraestructure.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCrudRepository extends CrudRepository<Product, Long> {
}
