package com.example.ordersservice.controller;

import com.example.ordersservice.domain.dto.OrdersDTO;
import com.example.ordersservice.infraestructure.mappers.OrdersMapper;
import com.example.ordersservice.domain.service.OrdersService;
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

    @PostMapping
    public ResponseEntity<OrdersDTO> createOrder(@RequestBody OrdersDTO orderDTO) {
        var savedOrder = ordersService.createOrder(ordersMapper.toEntity(orderDTO));
        return ResponseEntity.ok(ordersMapper.toDTO(savedOrder));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersDTO> getOrderById(@PathVariable Long id) {
        return ordersService.getOrderById(id)
                .map(order -> ResponseEntity.ok(ordersMapper.toDTO(order)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<OrdersDTO>> listOrders(@RequestParam(required = false) Long customerId,
                                                      @RequestParam(required = false) String status) {
        var orders = ordersService.listOrders(customerId, status);
        return ResponseEntity.ok(orders.stream()
                .map(ordersMapper::toDTO)
                .toList());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long id, @RequestBody String status) {
        var updatedOrder = ordersService.updateOrderStatus(id, status);
        return ResponseEntity.ok(ordersMapper.toDTO(updatedOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        ordersService.deleteOrder(id);
        return ResponseEntity.ok("Order deleted successfully.");
    }
}
