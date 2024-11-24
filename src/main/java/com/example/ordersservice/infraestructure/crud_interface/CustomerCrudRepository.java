package com.example.ordersservice.infraestructure.crud_interface;

import com.example.ordersservice.infraestructure.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCrudRepository extends CrudRepository<Customer, Long> {
}
