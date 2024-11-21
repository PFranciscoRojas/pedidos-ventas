package com.example.ordersservice.controller;

import com.example.ordersservice.domain.dto.CustomerDTO;
import com.example.ordersservice.domain.service.CustomerService;
import com.example.ordersservice.infraestructure.mappers.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper customerMapper;

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        var savedCustomer = customerService.createCustomer(customerMapper.toEntity(customerDTO));
        return ResponseEntity.ok(customerMapper.toDTO(savedCustomer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
                .map(customer -> ResponseEntity.ok(customerMapper.toDTO(customer)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> listCustomers() {
        var customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers.stream().map(customerMapper::toDTO).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        var updatedCustomer = customerService.updateCustomer(id, customerMapper.toEntity(customerDTO));
        return ResponseEntity.ok(customerMapper.toDTO(updatedCustomer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Customer deleted successfully.");
    }
}
