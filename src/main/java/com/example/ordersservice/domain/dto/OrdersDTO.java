package com.example.ordersservice.domain.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrdersDTO {
    private Long orderId;
    private Long customerId;
    private LocalDateTime orderDate;
    private String status;
    private double totalAmount;
    private List<ProductDTO> orderDetails;

    // Constructor con parámetros
    public OrdersDTO(Long orderId, Long customerId, LocalDateTime orderDate, String status, double totalAmount, List<ProductDTO> orderDetails) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.status = status;
        this.totalAmount = totalAmount;
        this.orderDetails = orderDetails;
    }

    // Getters y Setters
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<ProductDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<ProductDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }
}