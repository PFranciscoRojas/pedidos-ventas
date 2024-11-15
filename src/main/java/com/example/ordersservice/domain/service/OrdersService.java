package com.example.ordersservice.domain.service;

import com.example.ordersservice.domain.repository.OrdersRepository;
import com.example.ordersservice.domain.repository.ProductRepository;
import com.example.ordersservice.infraestructure.entity.Orders;
import com.example.ordersservice.infraestructure.entity.OrdersDetail;
import com.example.ordersservice.infraestructure.entity.Product;
import com.example.ordersservice.exception.OrdersException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockService stockService;

    public Orders createOrder(Orders order) {
        if (order.getCustomerId() == null) {
            throw new OrdersException("Customer ID is required.");
        }

        if (order.getOrderDetails() == null || order.getOrderDetails().isEmpty()) {
            throw new OrdersException("Order must have at least one detail.");
        }

        for (OrdersDetail detail : order.getOrderDetails()) {
            if (detail.getProduct() == null || detail.getProduct().getProductId() == null) {
                throw new OrdersException("Product ID is required for order detail.");
            }

            Product product = productRepository.findById(detail.getProduct().getProductId())
                    .orElseThrow(() -> new OrdersException("Product not found for ID: " + detail.getProduct().getProductId()));

            if (!stockService.validateStock(product.getProductId(), detail.getQuantity())) {
                throw new OrdersException("Insufficient stock for product ID: " + product.getProductId());
            }

            detail.setProduct(product);
            detail.setPrice(product.getPrice());
            detail.setOrder(order);
        }

        double totalAmount = order.getOrderDetails().stream()
                .mapToDouble(detail -> detail.getPrice() * detail.getQuantity())
                .sum();

        order.setTotalAmount(totalAmount);
        order.setStatus("PENDING");

        return ordersRepository.save(order);
    }

    public Optional<Orders> getOrderById(Long orderId) {
        return ordersRepository.findById(orderId);
    }

    public List<Orders> listOrders(Long customerId, String status) {
        if (customerId != null) {
            return ordersRepository.findByCustomerId(customerId);
        } else if (status != null) {
            return ordersRepository.findByStatus(status);
        }
        return ordersRepository.findAll();
    }

    public Orders updateOrderStatus(Long orderId, String status) {
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new OrdersException("Order not found for ID: " + orderId));
        order.setStatus(status);
        return ordersRepository.save(order);
    }

    public void deleteOrder(Long orderId) {
        if (!ordersRepository.existsById(orderId)) {
            throw new OrdersException("Order not found for ID: " + orderId);
        }
        ordersRepository.deleteById(orderId);
    }
}
