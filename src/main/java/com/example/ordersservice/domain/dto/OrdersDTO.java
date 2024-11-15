package com.example.ordersservice.domain.dto;

import java.util.Date;
import java.util.List;

public class OrdersDTO {
    private Long orderId;
    private Long customerId;
    private Date orderDate;
    private String status;
    private double totalAmount;
    private List<OrdersDetailDTO> orderDetails;

    // Getters and Setters
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
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

    public List<OrdersDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrdersDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
