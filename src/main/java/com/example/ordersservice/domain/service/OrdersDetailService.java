package com.example.ordersservice.domain.service;

import com.example.ordersservice.domain.dto.OrdersDetailDTO;

import java.util.List;
import java.util.Optional;

public interface OrdersDetailService {

    /**
     * Obtener todos los detalles de las órdenes.
     *
     * @return una lista de OrdersDetailDTO
     */
    List<OrdersDetailDTO> getAllOrderDetails();

    /**
     * Obtener los detalles de una orden específica por su ID.
     *
     * @param id el ID del detalle de la orden
     * @return un Optional de OrdersDetailDTO
     */
    Optional<OrdersDetailDTO> getOrderDetailById(Long id);

    /**
     * Crear un nuevo detalle de orden.
     *
     * @param ordersDetailDTO el DTO con la información del detalle de la orden
     * @return el DTO del detalle de la orden creada
     */
    OrdersDetailDTO createOrderDetail(OrdersDetailDTO ordersDetailDTO);

    /**
     * Eliminar un detalle de orden por su ID.
     *
     * @param id el ID del detalle de la orden a eliminar
     */
    void deleteOrderDetail(Long id);
}
