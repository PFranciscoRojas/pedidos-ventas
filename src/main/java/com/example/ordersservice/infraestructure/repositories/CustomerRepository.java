package com.example.ordersservice.infraestructure.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.ordersservice.infraestructure.crud_interface.CustomerCrudRepository;
import com.example.ordersservice.infraestructure.entity.Customer;
import com.example.ordersservice.domain.dto.CustomerDTO;
import com.example.ordersservice.domain.repository.CustomerRepositoryInterface;
import com.example.ordersservice.infraestructure.mappers.CustomerMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CustomerRepository implements CustomerRepositoryInterface {

    @Autowired
    private CustomerCrudRepository customerCrudRepository;

    @Autowired
    private CustomerMapper customerMapper;

    /**
     * Obtiene todos los clientes como una lista de objetos CustomerDTO.
     *
     * @return Lista de CustomerDTO.
     */
    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = (List<Customer>) customerCrudRepository.findAll();
        return customers.stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca un cliente por su ID.
     *
     * @param id ID del cliente.
     * @return Optional que contiene el CustomerDTO si existe, o vacío si no.
     */
    @Override
    public Optional<CustomerDTO> getCustomerById(Long id) {
        return customerCrudRepository.findById(id)
                .map(customerMapper::toDTO);
    }

    /**
     * Guarda un cliente nuevo o actualiza uno existente.
     *
     * @param customerDTO DTO del cliente a guardar.
     * @return CustomerDTO guardado o actualizado.
     */
    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        Customer savedCustomer = customerCrudRepository.save(customer);
        return customerMapper.toDTO(savedCustomer);
    }

    /**
     * Elimina un cliente específico por su ID.
     *
     * @param id ID del cliente a eliminar.
     */
    @Override
    public void deleteCustomer(Long id) {
        customerCrudRepository.deleteById(id);
    }
}
