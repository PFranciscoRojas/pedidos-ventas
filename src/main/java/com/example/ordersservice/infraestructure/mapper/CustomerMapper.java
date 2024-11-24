package com.example.ordersservice.infraestructure.mapper;

import com.example.ordersservice.domain.dto.CustomerDTO;
import com.example.ordersservice.infraestructure.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    // Convierte un Customer a CustomerDTO
    public CustomerDTO toDTO(Customer customer) {
        if (customer == null) return null;
        return new CustomerDTO(
                customer.getCustomerId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress()
        );
    }

    // Convierte un CustomerDTO a Customer
    public Customer toEntity(CustomerDTO customerDTO) {
        if (customerDTO == null) return null;
        Customer customer = new Customer();
        customer.setCustomerId(customerDTO.getCustomerId());
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());
        customer.setAddress(customerDTO.getAddress());
        return customer;
    }
}
