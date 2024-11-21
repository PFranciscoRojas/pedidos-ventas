package com.example.ordersservice.controller;

import com.example.ordersservice.domain.dto.OrdersDTO;
import com.example.ordersservice.domain.service.OrdersService;
import com.example.ordersservice.domain.service.CustomerService;
import com.example.ordersservice.infraestructure.mappers.OrdersMapper;
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

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<OrdersDTO> createOrder(@RequestBody OrdersDTO ordersDTO) {
        if (ordersDTO.getCustomerId() == null) {
            throw new IllegalArgumentException("CustomerId is required");
        }

        var customer = customerService.getCustomerById(ordersDTO.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + ordersDTO.getCustomerId()));

        var order = ordersMapper.toEntity(ordersDTO);
        order.setCustomer(customer);

        var savedOrder = ordersService.createOrder(order);
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
        return ResponseEntity.ok(orders.stream().map(ordersMapper::toDTO).toList());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrdersDTO> updateOrderStatus(@PathVariable Long id, @RequestBody String status) {
        var updatedOrder = ordersService.updateOrderStatus(id, status);
        return ResponseEntity.ok(ordersMapper.toDTO(updatedOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        ordersService.deleteOrder(id);
        return ResponseEntity.ok("Order deleted successfully.");
    }
}
