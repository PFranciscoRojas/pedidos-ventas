package com.example.ordersservice.controller;

import com.example.ordersservice.domain.dto.OrdersDetailDTO;
import com.example.ordersservice.domain.service.OrdersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders/details")
public class OrdersDetailController {

    @Autowired
    private OrdersDetailService ordersDetailService;

    @GetMapping
    public ResponseEntity<List<OrdersDetailDTO>> getAllOrderDetails() {
        return ResponseEntity.ok(ordersDetailService.getAllOrderDetails());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersDetailDTO> getOrderDetailById(@PathVariable Long id) {
        return ordersDetailService.getOrderDetailById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrdersDetailDTO> createOrderDetail(@RequestBody OrdersDetailDTO ordersDetailDTO) {
        return ResponseEntity.ok(ordersDetailService.createOrderDetail(ordersDetailDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable Long id) {
        ordersDetailService.deleteOrderDetail(id);
        return ResponseEntity.noContent().build();
    }
}
