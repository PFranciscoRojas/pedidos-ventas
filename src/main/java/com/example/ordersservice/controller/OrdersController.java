package com.example.ordersservice.controller;

import com.example.ordersservice.domain.dto.OrdersDTO;
import com.example.ordersservice.domain.service.OrdersService;
import com.example.ordersservice.infraestructure.entity.Customer;
import com.example.ordersservice.infraestructure.mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private OrdersMapper ordersMapper;

    /**
     * Obtener todos los pedidos
     */
    @GetMapping
    public ResponseEntity<List<OrdersDTO>> getAllOrders() {
        return ResponseEntity.ok(
                ordersService.listOrders(null, null).stream()
                        .map(ordersMapper::toDTO)
                        .toList()
        );
    }

    /**
     * Obtener pedido por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrdersDTO> getOrderById(@PathVariable Long id) {
        return ordersService.getOrderById(id)
                .map(ordersMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crear un nuevo pedido
     */
    @PostMapping
    public ResponseEntity<OrdersDTO> createOrder(@RequestBody OrdersDTO ordersDTO) {
        // Crear una instancia de Customer para mapear correctamente
        Customer customer = new Customer();
        customer.setCustomerId(ordersDTO.getCustomerId());

        // Crear el pedido usando el servicio
        var savedOrder = ordersService.createOrder(ordersMapper.toEntity(ordersDTO, customer));
        return ResponseEntity.ok(ordersMapper.toDTO(savedOrder));
    }

    /**
     * Actualizar el estado de un pedido
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<OrdersDTO> updateOrderStatus(@PathVariable Long id, @RequestBody String status) {
        var updatedOrder = ordersService.updateOrderStatus(id, status);
        return ResponseEntity.ok(ordersMapper.toDTO(updatedOrder));
    }

    /**
     * Eliminar un pedido
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        ordersService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
