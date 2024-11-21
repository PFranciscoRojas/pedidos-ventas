package com.example.ordersservice.domain.repository;

import com.example.ordersservice.domain.dto.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerRepositoryInterface {
    List<CustomerDTO> getAllCustomers();
    Optional<CustomerDTO> getCustomerById(Long id);
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    void deleteCustomer(Long id);
}
