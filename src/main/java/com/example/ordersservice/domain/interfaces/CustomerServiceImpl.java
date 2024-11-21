package com.example.ordersservice.domain.interfaces;

import com.example.ordersservice.domain.service.CustomerService;
import com.example.ordersservice.infraestructure.crud_interface.CustomerCrudRepository;
import com.example.ordersservice.infraestructure.entity.Customer;
import com.example.ordersservice.exception.OrdersException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerCrudRepository customerCrudRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerCrudRepository.save(customer);
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerCrudRepository.findById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return StreamSupport.stream(customerCrudRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        if (!customerCrudRepository.existsById(id)) {
            throw new OrdersException("Customer not found for ID: " + id);
        }
        customer.setCustomerId(id);
        return customerCrudRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!customerCrudRepository.existsById(id)) {
            throw new OrdersException("Customer not found for ID: " + id);
        }
        customerCrudRepository.deleteById(id);
    }
}
