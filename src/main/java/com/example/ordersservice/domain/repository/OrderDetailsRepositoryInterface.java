package com.example.ordersservice.domain.repository;

import com.example.ordersservice.domain.dto.OrdersDetailDTO;

import java.util.List;
import java.util.Optional;

public interface OrderDetailsRepositoryInterface {

    /**
     * Obtiene todos los detalles de los pedidos.
     *
     * @return Lista de OrdersDetailDTO.
     */
    List<OrdersDetailDTO> getAllOrderDetails();

    /**
     * Busca un detalle de pedido por su ID.
     *
     * @param id ID del detalle de pedido.
     * @return Optional que contiene el OrdersDetailDTO si existe, o vacío si no.
     */
    Optional<OrdersDetailDTO> getOrderDetailById(Long id);

    /**
     * Guarda un detalle de pedido nuevo o actualiza uno existente.
     *
     * @param ordersDetailDTO DTO del detalle de pedido a guardar.
     * @return OrdersDetailDTO guardado o actualizado.
     */
    OrdersDetailDTO saveOrderDetail(OrdersDetailDTO ordersDetailDTO);

    /**
     * Elimina un detalle de pedido específico por su ID.
     *
     * @param id ID del detalle de pedido a eliminar.
     */
    void deleteOrderDetail(Long id);
}
