package com.example.ordersservice.domain.service;

import org.springframework.stereotype.Service;

@Service
public class StockService {

    public boolean validateStock(Long productId, int quantity) {

        return quantity <= 10; 
    }
}
