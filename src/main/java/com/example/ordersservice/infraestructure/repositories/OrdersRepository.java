package com.example.ordersservice.infraestructure.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.ordersservice.domain.dto.OrdersDTO;
import com.example.ordersservice.domain.repository.OrdersRepositoryInterface;
import com.example.ordersservice.infraestructure.crud_interface.OrdersCrudRepository;
import com.example.ordersservice.infraestructure.entity.Orders;
import com.example.ordersservice.infraestructure.mappers.OrdersMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class OrdersRepository implements OrdersRepositoryInterface {

    @Autowired
    private OrdersCrudRepository ordersCrudRepository;

    @Autowired
    private OrdersMapper ordersMapper;

    /**
     * Obtiene todos los pedidos como una lista de objetos OrdersDTO.
     *
     * @return Lista de OrdersDTO.
     */
    @Override
    public List<OrdersDTO> getAllOrders() {
        List<Orders> orders = (List<Orders>) ordersCrudRepository.findAll();
        return orders.stream()
                .map(ordersMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca un pedido por su ID.
     *
     * @param id ID del pedido.
     * @return Optional que contiene el OrdersDTO si existe, o vacío si no.
     */
    @Override
    public Optional<OrdersDTO> getOrderById(Long id) {
        return ordersCrudRepository.findById(id)
                .map(ordersMapper::toDTO);
    }

    /**
     * Guarda un pedido nuevo o actualiza uno existente.
     *
     * @param ordersDTO DTO del pedido a guardar.
     * @return OrdersDTO guardado o actualizado.
     */
    @Override
    public OrdersDTO saveOrder(OrdersDTO ordersDTO) {
        Orders order = ordersMapper.toEntity(ordersDTO);
        Orders savedOrder = ordersCrudRepository.save(order);
        return ordersMapper.toDTO(savedOrder);
    }

    /**
     * Elimina un pedido específico por su ID.
     *
     * @param id ID del pedido a eliminar.
     */
    @Override
    public void deleteOrder(Long id) {
        ordersCrudRepository.deleteById(id);
    }

    /**
     * Obtiene los pedidos realizados por un cliente específico.
     *
     * @param customerId ID del cliente.
     * @return Lista de OrdersDTO de ese cliente.
     */
    @Override
    public List<OrdersDTO> getOrdersByCustomerId(Long customerId) {
        List<Orders> orders = ordersCrudRepository.findByCustomer_CustomerId(customerId);

        return orders.stream()
                .map(ordersMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene los pedidos por su estado.
     *
     * @param status Estado de los pedidos.
     * @return Lista de OrdersDTO con ese estado.
     */
    @Override
    public List<OrdersDTO> getOrdersByStatus(String status) {
        List<Orders> orders = ordersCrudRepository.findByStatus(status);
        return orders.stream()
                .map(ordersMapper::toDTO)
                .collect(Collectors.toList());
    }
}
