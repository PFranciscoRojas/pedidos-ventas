package com.example.ordersservice.domain.repository;

import com.example.ordersservice.domain.dto.OrdersDTO;

import java.util.List;
import java.util.Optional;

public interface OrdersRepositoryInterface {

    /**
     * Obtiene todos los pedidos como una lista de objetos OrdersDTO.
     *
     * @return Lista de OrdersDTO.
     */
    List<OrdersDTO> getAllOrders();

    /**
     * Busca un pedido por su ID.
     *
     * @param id ID del pedido.
     * @return Optional que contiene el OrdersDTO si existe, o vacío si no.
     */
    Optional<OrdersDTO> getOrderById(Long id);

    /**
     * Guarda un pedido nuevo o actualiza uno existente.
     *
     * @param ordersDTO DTO del pedido a guardar.
     * @return OrdersDTO guardado o actualizado.
     */
    OrdersDTO saveOrder(OrdersDTO ordersDTO);

    /**
     * Elimina un pedido específico por su ID.
     *
     * @param id ID del pedido a eliminar.
     */
    void deleteOrder(Long id);

    /**
     * Obtiene los pedidos asociados a un cliente específico.
     *
     * @param customerId ID del cliente.
     * @return Lista de OrdersDTO asociados al cliente.
     */
    List<OrdersDTO> getOrdersByCustomerId(Long customerId);

    /**
     * Obtiene los pedidos filtrados por estado.
     *
     * @param status Estado de los pedidos.
     * @return Lista de OrdersDTO con el estado especificado.
     */
    List<OrdersDTO> getOrdersByStatus(String status);
}
