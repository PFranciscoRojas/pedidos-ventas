package com.example.ordersservice.domain.repository;

import com.example.ordersservice.domain.dto.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerRepositoryInterface {

    /**
     * Obtiene todos los clientes como una lista de objetos CustomerDTO.
     *
     * @return Lista de CustomerDTO.
     */
    List<CustomerDTO> getAllCustomers();

    /**
     * Busca un cliente por su ID.
     *
     * @param id ID del cliente.
     * @return Optional que contiene el CustomerDTO si existe, o vacío si no.
     */
    Optional<CustomerDTO> getCustomerById(Long id);

    /**
     * Guarda un cliente nuevo o actualiza uno existente.
     *
     * @param customerDTO DTO del cliente a guardar.
     * @return CustomerDTO guardado o actualizado.
     */
    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    /**
     * Elimina un cliente específico por su ID.
     *
     * @param id ID del cliente a eliminar.
     */
    void deleteCustomer(Long id);
}
